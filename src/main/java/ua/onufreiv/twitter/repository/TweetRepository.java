package ua.onufreiv.twitter.repository;

import ua.onufreiv.twitter.domain.User;

import java.util.List;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
public interface TweetRepository<T> {
    boolean create(T t);

    Iterable findAll();

    T findByID(int id);

    List<T> findUserTweets(User user);
}
