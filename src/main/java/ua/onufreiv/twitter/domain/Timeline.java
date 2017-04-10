package ua.onufreiv.twitter.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
@Component
public class Timeline {
    private final List<Tweet> tweets;

    public Timeline() {
        tweets = new ArrayList<>();
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public Tweet makeRetweet(Tweet tweet) {
        return tweet.retweet();
    }

    public int likeTweet(Tweet tweet) {
        return tweet.like();
    }

    public List<Tweet> userTimeline(User user) {
        List<Tweet> userTimeline = new ArrayList<>();

        for (Tweet tweet : tweets) {
            if(tweet.getUser().equals(user)) {
                userTimeline.add(tweet);
            }
        }

        return userTimeline;
    }

    public Iterable<Tweet> tweets() {
        return new ArrayList<>(tweets);
    }
}
