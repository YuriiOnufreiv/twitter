package ua.onufreiv.twitter.infrastructure;

import ua.onufreiv.twitter.infrastructure.annotations.Benchmark;
import ua.onufreiv.twitter.infrastructure.annotations.PostConstructBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
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

        BeanBuilder beanBuilder = new BeanBuilder(clazz);
        beanBuilder.createBean();
        beanBuilder.callPostCreateMethod();
        beanBuilder.callInitMethod();
        beanBuilder.createBeanProxy();
        bean = (T) beanBuilder.build();

        beans.put(beanName, bean);
        return bean;
    }

    private class BeanBuilder<T> {
        private final Class clazz;
        private T bean;
        private T beanProxy;

        private BeanBuilder(Class clazz) {
            this.clazz = clazz;
        }

        private void createBean() throws Exception {
            Constructor<?> constructor = clazz.getConstructors()[0];

            if (constructor.getParameterCount() == 0) {
                bean = getInstanceWithoutDependencies();
            } else {
                bean = getInstanceWithDependencies(constructor);
            }
        }

        private T getInstanceWithoutDependencies() throws Exception {
            return (T) clazz.newInstance();
        }

        private T getInstanceWithDependencies(Constructor<?> constructor) throws Exception {
            Class[] parameterTypes = constructor.getParameterTypes();
            Object[] params = new Object[constructor.getParameterCount()];

            for (int i = 0; i < parameterTypes.length; i++) {
                Class parameterClass = parameterTypes[i];
                String className = parameterClass.getSimpleName();
                String beanName = getBeanNameByClass(className);

                Object param = ApplicationContext.this.getBean(beanName);
                params[i] = (param);
            }

            return (T) constructor.newInstance(params);
        }

        private String getBeanNameByClass(String className) {
            return className.substring(0, 1).toLowerCase() + className.substring(1);
        }

        private void callPostCreateMethod() throws Exception {
            throwExceptionIfBeanIsNull();

            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (method.isAnnotationPresent(PostConstructBean.class)) {
                    method.invoke(bean);
                }
            }
        }

        private void callInitMethod() throws Exception {
            throwExceptionIfBeanIsNull();

            Method initMethod;
            try {
                initMethod = clazz.getMethod("init");
            } catch (NoSuchMethodException e) {
                return;
            }
            initMethod.invoke(bean);
        }

        private void createBeanProxy() {
            throwExceptionIfBeanIsNull();

            beanProxy = (T) Proxy.newProxyInstance(
                    clazz.getClassLoader(),
                    clazz.getInterfaces(),
                    (proxy, method, args) -> {
                        Method parentMethod = clazz.getMethod(method.getName(), method.getParameterTypes());
                        Benchmark annotation = parentMethod.getAnnotation(Benchmark.class);

                        if (annotation != null && annotation.value()) {
                            long startTime = System.nanoTime();
                            Object returnValue = method.invoke(bean, args);
                            long execTime = System.nanoTime() - startTime;
                            System.out.println("Benchmark for \'" + parentMethod + "\' [" + execTime + " nanos]");
                            return returnValue;
                        }

                        return method.invoke(bean, args);
                    });
        }

        private T build() {
            throwExceptionIfBeanIsNull();

            return beanProxy;
        }

        private void throwExceptionIfBeanIsNull() {
            if (bean == null) {
                throw new NullPointerException("Bean is not created");
            }
        }
    }

}
