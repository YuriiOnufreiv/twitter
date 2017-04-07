package ua.onufreiv.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.infrastructure.annotations.Benchmark;
import ua.onufreiv.twitter.repository.TweetRepository;

/**
 * Created by Yurii_Onufreiv on 31-Mar-17.
 */
@Component
public class TweetServiceImpl implements TweetService {
    private TweetRepository tweetRepository;

    public TweetServiceImpl() {
    }

    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Autowired
    private void setTweetRepository(TweetRepository tweetRepository) {
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
