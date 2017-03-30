package ua.onufreiv.twitter;

import ua.onufreiv.twitter.infrastructure.ApplicationContext;
import ua.onufreiv.twitter.infrastructure.Context;
import ua.onufreiv.twitter.infrastructure.JavaConfig;
import ua.onufreiv.twitter.repository.TweetRepository;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
public class TwitterRunner {
    public static void main(String[] args) throws Exception {
        Context ctx = new ApplicationContext(new JavaConfig());
        TweetRepository tweetRepository = ctx.getBean("tweetRepository");

//        System.out.println(tweetRepository == ctx.getBean("tweetRepository"));

        tweetRepository.findAll().forEach(System.out::println);
    }
}
