package ua.onufreiv.twitter.domain.service;

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
    @Benchmark
    public boolean create(Tweet tweet) {
        return tweetRepository.create(tweet);
    }

    @Override
    public Iterable findAll() {
        return tweetRepository.findAll();
    }
}
