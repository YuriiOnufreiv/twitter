package ua.onufreiv.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.service.TweetService;

import java.util.Optional;

/**
 * Created by Yurii_Onufreiv on 19-Apr-17.
 */
public class TweetConverter implements Converter<String, Tweet> {
    @Autowired
    private TweetService tweetService;

    @Override
    public Tweet convert(String text) {
        Long id = Long.valueOf(text);
        Optional<Tweet> tweet = tweetService.findById(id);
        return tweet.orElse(null);
    }
}
