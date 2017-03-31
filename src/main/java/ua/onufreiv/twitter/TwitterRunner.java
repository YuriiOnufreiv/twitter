package ua.onufreiv.twitter;

import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.domain.User;
import ua.onufreiv.twitter.domain.service.TweetService;
import ua.onufreiv.twitter.infrastructure.ApplicationContext;
import ua.onufreiv.twitter.infrastructure.Context;
import ua.onufreiv.twitter.infrastructure.JavaConfig;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
public class TwitterRunner {
    public static void main(String[] args) throws Exception {
        Context context = new ApplicationContext(new JavaConfig());
        TweetService tweetService = context.getBean("tweetService");

        tweetService.findAll().forEach(System.out::println);

        tweetService.create(new Tweet(new User("Test"), "My first test"));
        tweetService.findAll().forEach(System.out::println);
    }
}
