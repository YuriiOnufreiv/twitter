package ua.onufreiv.twitter.domain;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
public class User {
    private String username;

    public User(String username) {
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
