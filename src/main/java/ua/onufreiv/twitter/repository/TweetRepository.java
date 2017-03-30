package ua.onufreiv.twitter.repository;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
public interface TweetRepository<T> {
    boolean create(T t);

    Iterable findAll();


}
