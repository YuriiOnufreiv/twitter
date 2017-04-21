package ua.onufreiv.twitter.repository;

import org.springframework.stereotype.Component;
import ua.onufreiv.twitter.domain.User;
import ua.onufreiv.twitter.infrastructure.annotations.Benchmark;
import ua.onufreiv.twitter.infrastructure.annotations.PostConstructBean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
@Component("userRepository")
public class InMemUserRepository implements UserRepository<User> {
    private final List<User> users = new ArrayList<>();

    @PostConstructBean
    @PostConstruct
    public void initialize() {
        save(new User("Epamer"));
        save(new User("Googler"));
    }

    @Override
    public boolean save(User user) {
        Long id = user.getId();
        if(id == null) {
            user.setId((long) users.size());
            users.add(user);
        } else {
            users.set(id.intValue(), user);
        }
        return true;
    }

    @Benchmark
    @Override
    public Iterable findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public Optional<User> findByID(Long id) {
        Optional<User> user;

        try {
            user = Optional.ofNullable(users.get(id.intValue()));
        } catch (NullPointerException e) {
            user = Optional.empty();
        }

        return user;

//        for (Tweet tweet : users) {
//            if(tweet.getId().equals(id)) {
//                return Optional.ofNullable(tweet);
//            }
//        }
//
//        return Optional.empty();
    }
}
