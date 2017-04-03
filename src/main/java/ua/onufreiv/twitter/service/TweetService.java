package ua.onufreiv.twitter.service;

import ua.onufreiv.twitter.domain.Tweet;

/**
 * Created by Yurii_Onufreiv on 31-Mar-17.
 */
public interface TweetService {
    boolean create(Tweet tweet);

    Iterable findAll();
}
