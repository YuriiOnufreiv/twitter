package ua.onufreiv.twitter.service;

import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.domain.User;

import java.util.Optional;

/**
 * Created by Yurii_Onufreiv on 31-Mar-17.
 */
public interface TweetService {
    boolean create(Tweet tweet);

    Tweet create(User user, String text);

    Tweet createEmptyTweet();

    Iterable findAll();

    Optional<Tweet> findById(Long id);
}
