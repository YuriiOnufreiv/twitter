package ua.onufreiv.twitter.domain;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
public class User {
    private String username;
    private Profile profile;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Profile getProfile() {
        return profile;
    }

    @Override
    public String toString() {
        return "\'" + username + '\'';
    }

    public class Profile {

    }
}
