package ua.onufreiv.twitter.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
@Component
@Scope("prototype")
public class User {
    private String username;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "\'" + username + '\'';
    }
}
