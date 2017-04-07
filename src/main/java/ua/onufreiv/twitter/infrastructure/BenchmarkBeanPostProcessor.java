package ua.onufreiv.twitter.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ua.onufreiv.twitter.infrastructure.annotations.Benchmark;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Yurii Onufreiv
 * @version 1.0
 */
public class BenchmarkBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method : methods) {
            Benchmark annotation = method.getAnnotation(Benchmark.class);

            if(annotation != null && annotation.value()) {
                System.out.println("-BBPP- Adding proxy to " + clazz + "." + method.getName());
                return createProxyInstance(bean);
            }
        }

        return bean;
    }

    private Object createProxyInstance(Object bean) {
        Class<?> clazz = bean.getClass();
        return Proxy.newProxyInstance(
                clazz.getClassLoader(),
                clazz.getInterfaces(),
                (proxy, method, args) -> {
                    Method parentMethod = clazz.getMethod(method.getName(), method.getParameterTypes());
                    Benchmark annotation = parentMethod.getAnnotation(Benchmark.class);

                    if (annotation != null && annotation.value()) {
                        long startTime = System.nanoTime();
                        Object returnValue = method.invoke(bean, args);
                        long execTime = System.nanoTime() - startTime;
                        System.out.println("-BBPP- Benchmark for \'" + parentMethod + "\' [" + execTime + " nanos]");
                        return returnValue;
                    }

                    return method.invoke(bean, args);
                });
    }
}
