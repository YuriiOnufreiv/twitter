package ua.onufreiv.twitter;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.onufreiv.twitter.domain.User;
import ua.onufreiv.twitter.service.TweetService;

/**
 * Created by Yurii_Onufreiv on 03-Apr-17.
 */
public class SpringTwitterRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext reposContext
                = new ClassPathXmlApplicationContext("repos.xml");
        ConfigurableApplicationContext serviceContext
                = new ClassPathXmlApplicationContext(new String[] {"services.xml"}, reposContext);

//        ConfigurableApplicationContext reposContext
//                = new AnnotationConfigApplicationContext(ReposConfig.class);
//        ConfigurableApplicationContext serviceContext
//                = new AnnotationConfigApplicationContext(ServicesConfig.class);

        TweetService tweetService = (TweetService) serviceContext.getBean("tweetService");
        User user = (User) reposContext.getBean("user");
        user.setName("Microsofter");
        tweetService.create(user, "I like WINDOWS!!!");
        tweetService.findAll().forEach(System.out::println);

        reposContext.close();
    }
}
