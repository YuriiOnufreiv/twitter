package ua.onufreiv.twitter.service;

import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.domain.User;

/**
 * Created by Yurii_Onufreiv on 31-Mar-17.
 */
public interface TweetService {
    boolean create(Tweet tweet);

    Tweet create(User user, String text);

    Tweet createEmptyTweet();

    Iterable findAll();

    Tweet getById(int id);
}
