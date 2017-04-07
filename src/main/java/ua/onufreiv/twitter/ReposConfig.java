package ua.onufreiv.twitter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ua.onufreiv.twitter.repository.InMemTweetRepository;
import ua.onufreiv.twitter.repository.TweetRepository;

/**
 * @author Yurii Onufreiv
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages="ua.onufreiv.twitter")
public class ReposConfig {

    @Bean
    public TweetRepository tweetRepository() {
        return new InMemTweetRepository();
    }
}
