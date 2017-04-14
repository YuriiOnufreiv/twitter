package ua.onufreiv.twitter.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.onufreiv.twitter.service.TweetService;
import ua.onufreiv.twitter.service.impl.TweetServiceImpl;

/**
 * @author Yurii Onufreiv
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages="ua.onufreiv.twitter")
public class ServicesConfig {

    @Bean
    public TweetService tweetService() {
        return new TweetServiceImpl();
    }
}
