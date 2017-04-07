package ua.onufreiv.twitter.repository;

import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.domain.User;
import ua.onufreiv.twitter.infrastructure.annotations.Benchmark;
import ua.onufreiv.twitter.infrastructure.annotations.PostConstructBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
public class InMemTweetRepository implements TweetRepository<Tweet> {
    private final List<Tweet> tweets = new ArrayList<>();

    @PostConstructBean
    public void initialize() {
        create(new Tweet(new User("Epamer"), "My first tweet"));
        create(new Tweet(new User("Googler"), "My second tweet"));
    }

    @Override
    public boolean create(Tweet tweet) {
        return tweets.add(tweet);
    }

    @Benchmark
    @Override
    public Iterable findAll() {
        return new ArrayList<>(tweets);
    }
}
