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
    private TweetRepository<Tweet> tweetRepository;

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
    public Tweet create(User user, String text) {
        Tweet tweet = createEmptyTweet();
        tweet.setUser(user);
        tweet.setText(text);
        tweetRepository.create(tweet);
        return tweet;
    }

    @Lookup("tweet")
    @Override
    public Tweet createEmptyTweet() {
        return null;
    }

    @Benchmark
    @Override
    public Iterable findAll() {
        return tweetRepository.findAll();
    }

    public Tweet getTweetById(int id) {
        return tweetRepository.findByID(id);
    }

    public Tweet createReplyTweet(User user, String text, Tweet replyToTweet) {
        Tweet tweet = create(user, text);
        tweet.setInReplyToTweet(replyToTweet);
        return tweet;
    }
}
