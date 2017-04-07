package ua.onufreiv.twitter.service;

import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.infrastructure.annotations.Benchmark;
import ua.onufreiv.twitter.repository.TweetRepository;

/**
 * Created by Yurii_Onufreiv on 31-Mar-17.
 */
public class TweetServiceImpl implements TweetService {
    private TweetRepository tweetRepository;

    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public boolean create(Tweet tweet) {
        return tweetRepository.create(tweet);
    }

    @Benchmark
    @Override
    public Iterable findAll() {
        return tweetRepository.findAll();
    }
}
