package ua.onufreiv.twitter.repository;

import org.springframework.stereotype.Component;
import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.domain.User;
import ua.onufreiv.twitter.infrastructure.annotations.Benchmark;
import ua.onufreiv.twitter.infrastructure.annotations.PostConstructBean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
@Component("tweetRepository")
public class InMemTweetRepository implements TweetRepository<Tweet> {
    private final List<Tweet> tweets = new ArrayList<>();

    @PostConstructBean
    @PostConstruct
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

    @Override
    public Tweet findByID(int id) {
        for (Tweet tweet : tweets) {
            if(tweet.getId() == id) {
                return tweet;
            }

        }

        return null;
    }

    @Override
    public List<Tweet> findUserTweets(User user) {
        List<Tweet> userTweets = new ArrayList<>();
        for(Tweet tweet : tweets) {
            if(tweet.getUser().getName().equals(user.getName())) {
                userTweets.add(tweet);
            }
        }
        return userTweets;
    }
}
