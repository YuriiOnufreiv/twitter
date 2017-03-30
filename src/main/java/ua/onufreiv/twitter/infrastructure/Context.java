package ua.onufreiv.twitter.infrastructure;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
public interface Context {
    <T> T getBean(String beanName) throws Exception;
}
