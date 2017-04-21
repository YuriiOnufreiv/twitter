package ua.onufreiv.twitter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.onufreiv.twitter.domain.User;
import ua.onufreiv.twitter.repository.UserRepository;
import ua.onufreiv.twitter.service.UserService;

import java.util.Optional;

/**
 * Created by Yurii_Onufreiv on 21-Apr-17.
 */
@Component("userService")
public class UserServiceImpl implements UserService {
    private UserRepository<User> userRepository;

    @Autowired
    public UserServiceImpl(UserRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Iterable findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findByID(id);
    }

    @Override
    public void update(User user) {

    }
}
