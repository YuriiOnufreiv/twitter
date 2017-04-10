package ua.onufreiv.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.domain.User;
import ua.onufreiv.twitter.infrastructure.annotations.Benchmark;
import ua.onufreiv.twitter.repository.TweetRepository;

/**
 * Created by Yurii_Onufreiv on 31-Mar-17.
 */
@Component("tweetService")
public class TweetServiceImpl implements TweetService {
    private TweetRepository tweetRepository;

    public TweetServiceImpl() {
    }

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public boolean create(Tweet tweet) {
        return tweetRepository.create(tweet);
    }

    @Override
    public boolean create(User user, String text) {
        Tweet tweet = createEmptyTweet();
        tweet.setUser(user);
        tweet.setText(text);
        return tweetRepository.create(tweet);
    }

    @Lookup("tweet")
    public Tweet createEmptyTweet() {
        return null;
    }

    @Benchmark
    @Override
    public Iterable findAll() {
        return tweetRepository.findAll();
    }
}
