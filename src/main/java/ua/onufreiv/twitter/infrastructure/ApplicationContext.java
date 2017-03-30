package ua.onufreiv.twitter.infrastructure;

import java.lang.reflect.InvocationHandler;
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
        bean = (T) clazz.newInstance();
//        bean = createProxy(bean);

        callInitMethod(bean);
        callPostConstructBean(bean);

        beans.put(beanName, bean);
        return bean;
    }

//    private <T> T createProxy(T bean) {
//        T newBean = (T) Proxy.newProxyInstance(bean.getClass().getClassLoader(),
//                bean.getClass().getInterfaces(),
//                new InvocationHandler() {
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        boolean annotationPresent = method.isAnnotationPresent(Benchmark.class);
//                        if(annotationPresent) {
//
//                        }
//
//                        method.invoke(bean);
//
//                        if(annotationPresent) {
//
//                        }
//                        return null;
//                    }
//                });
//        return newBean;
//    }

    private <T> void callPostConstructBean(T bean) throws Exception {
        Class<?> clazz = bean.getClass();

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(PostConstructBean.class)) {
                method.invoke(bean);
            }
        }
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
}
