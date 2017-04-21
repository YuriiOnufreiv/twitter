package ua.onufreiv.twitter.repository;

import ua.onufreiv.twitter.domain.User;

import java.util.Optional;

/**
 * Created by Yurii_Onufreiv on 21-Apr-17.
 */
public interface UserRepository<T> {
    boolean save(User user);

    Iterable findAll();

    Optional<User> findByID(Long id);
}
