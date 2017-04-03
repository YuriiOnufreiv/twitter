package ua.onufreiv.twitter;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
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
                = new ClassPathXmlApplicationContext("repos.xml");
        ConfigurableApplicationContext serviceContext
                = new ClassPathXmlApplicationContext(new String[] {"services.xml"}, reposContext);

        TweetRepository repository = (TweetRepository) reposContext.getBean("tweetRepository");

        // beans in container
        System.out.println(Arrays.toString(reposContext.getBeanDefinitionNames()));

        // info about bean
        BeanDefinition repoBeanDefinition = reposContext.getBeanFactory().getBeanDefinition("tweetRepository");
        System.out.println(repoBeanDefinition);

        // change bean property dynamically
        repoBeanDefinition.setScope("prototype");
        repoBeanDefinition = reposContext.getBeanFactory().getBeanDefinition("tweetRepository");
        System.out.println(repoBeanDefinition);

        TweetService tweetService = (TweetService) serviceContext.getBean("tweetService");
        tweetService.findAll().forEach(System.out::println);

        reposContext.close();
    }
}
