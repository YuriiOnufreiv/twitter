package ua.onufreiv.twitter.repository;

import org.springframework.stereotype.Component;
import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.domain.User;
import ua.onufreiv.twitter.infrastructure.annotations.Benchmark;
import ua.onufreiv.twitter.infrastructure.annotations.PostConstructBean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Tweet> findByID(Long id) {
        Optional<Tweet> tweet;

        try {
            tweet = Optional.ofNullable(tweets.get(id.intValue()));
        } catch (NullPointerException e) {
            tweet = Optional.empty();
        }

        return tweet;

//        for (Tweet tweet : tweets) {
//            if(tweet.getTweetId().equals(id)) {
//                return Optional.ofNullable(tweet);
//            }
//        }
//
//        return Optional.empty();
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
