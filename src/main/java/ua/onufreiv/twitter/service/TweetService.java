package ua.onufreiv.twitter.service;

import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.domain.User;

/**
 * Created by Yurii_Onufreiv on 31-Mar-17.
 */
public interface TweetService {
    boolean create(Tweet tweet);

    boolean create(User user, String text);

    Iterable findAll();
}
