package ua.onufreiv.twitter.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
@Component
@Scope("prototype")
public class User {
    private String name;
    private Profile profile;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Profile getProfile() {
        return profile;
    }

    @Override
    public String toString() {
        return "\'" + name + '\'';
    }

    public class Profile {

    }
}
