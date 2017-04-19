package ua.onufreiv.twitter.repository;

import ua.onufreiv.twitter.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
public interface TweetRepository<T> {
    boolean create(T t);

    Iterable findAll();

    Optional<T> findByID(Long id);

    List<T> findUserTweets(User user);
}
