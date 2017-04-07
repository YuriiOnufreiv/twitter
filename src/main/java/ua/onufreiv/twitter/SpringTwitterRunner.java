package ua.onufreiv.twitter;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.onufreiv.twitter.repository.TweetRepository;
import ua.onufreiv.twitter.service.TweetService;

import java.util.Arrays;

/**
 * Created by Yurii_Onufreiv on 03-Apr-17.
 */
public class SpringTwitterRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext reposContext
                = new AnnotationConfigApplicationContext(ReposConfig.class);
//                = new ClassPathXmlApplicationContext("repos.xml");
        ConfigurableApplicationContext serviceContext
                = new AnnotationConfigApplicationContext(ReposConfig.class);
//                = new ClassPathXmlApplicationContext(new String[] {"services.xml"}, reposContext);

        TweetRepository repository = (TweetRepository) reposContext.getBean("tweetRepository");

        TweetService tweetService = (TweetService) serviceContext.getBean("tweetService");
        tweetService.findAll().forEach(System.out::println);

        reposContext.close();
    }
}
