package ua.onufreiv.twitter.service;

import ua.onufreiv.twitter.domain.User;

import java.util.Optional;

/**
 * Created by Yurii_Onufreiv on 31-Mar-17.
 */
public interface UserService {
    boolean save(User user);

    Iterable findAll();

    Optional<User> findById(Long id);

    void update(User user);
}
