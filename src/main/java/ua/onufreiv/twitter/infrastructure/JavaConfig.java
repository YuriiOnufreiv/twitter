package ua.onufreiv.twitter.infrastructure;

import ua.onufreiv.twitter.domain.service.TweetServiceImpl;
import ua.onufreiv.twitter.repository.InMemTweetRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
public class JavaConfig implements Config {
    private final Map<String, Class<?>> classes = new HashMap<>();

    {
        classes.put("tweetRepository", InMemTweetRepository.class);
        classes.put("tweetService", TweetServiceImpl.class);
    }

    @Override
    public Class<?> getImpl(String name) {
        return classes.get(name);
    }
}
