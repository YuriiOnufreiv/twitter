package ua.onufreiv.twitter.infrastructure;

import ua.onufreiv.twitter.infrastructure.annotations.Benchmark;
import ua.onufreiv.twitter.infrastructure.annotations.PostConstructBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
public class ApplicationContext implements Context {
    private final Config config;
    private final Map<String, Object> beans = new HashMap<>();

    public ApplicationContext(Config config) {
        this.config = config;
    }

    @Override
    public <T> T getBean(String beanName) throws Exception {
        T bean = (T) beans.get(beanName);

        if (bean != null) {
            return bean;
        }

        Class<?> clazz = config.getImpl(beanName);
        if (clazz == null) {
            throw new RuntimeException("Bean \'" + beanName + "\' not found");
        }
        bean = createBean(clazz);

        callInitMethod(bean);
        callPostConstructBean(bean);

        bean = createProxy(bean);
        beans.put(beanName, bean);
        return bean;
    }

    private <T> T createBean(Class clazz) throws Exception {
        Constructor<?> constructor = clazz.getConstructors()[0];

        Class[] parameterTypes = constructor.getParameterTypes();
        List<Object> params = new ArrayList<>();
        for (Class parameterClass : parameterTypes) {
            String className = parameterClass.getSimpleName();
            String beanName = className.substring(0, 1).toLowerCase() + className.substring(1);

            Object param = getBean(beanName);
            params.add(param);
        }

        if(params.size() > 0) {
            return (T) constructor.newInstance(params.toArray());
        }
        return (T) clazz.newInstance();
    }

    private <T> void callInitMethod(T bean) throws Exception {
        Class<?> clazz = bean.getClass();
        Method initMethod;
        try {
            initMethod = clazz.getMethod("init");
        } catch (NoSuchMethodException e) {
            return;
        }
        initMethod.invoke(bean);
    }

    private <T> void callPostConstructBean(T bean) throws Exception {
        Class<?> clazz = bean.getClass();

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(PostConstructBean.class)) {
                method.invoke(bean);
            }
        }
    }

    private <T> T createProxy(T bean) {
        T newBean = (T) Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    String methodName = method.getName();
                    Method parentMethod = bean.getClass().getMethod(methodName, method.getParameterTypes());
                    Benchmark annotation = parentMethod.getAnnotation(Benchmark.class);

                    if (annotation != null && annotation.value()) {
                        long startTime = 0;
                        startTime = System.nanoTime();
                        Object returnValue = method.invoke(bean, args);
                        long execTime = System.nanoTime() - startTime;
                        System.out.println("Benchmark for \'" + methodName + "\' [" + execTime + " nanos]");
                        return returnValue;
                    }

                    return method.invoke(bean, args);
                });
        return newBean;
    }
}
