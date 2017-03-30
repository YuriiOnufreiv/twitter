package ua.onufreiv.twitter.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
public class Timeline {
    private final List<Tweet> tweets;

    public Timeline() {
        tweets = new ArrayList<>();
    }

    public void put(Tweet tweet) {
        tweets.add(tweet);
    }

    public Iterable<Tweet> tweets() {
        return new ArrayList<>(tweets);
    }
}
