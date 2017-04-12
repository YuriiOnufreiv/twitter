package ua.onufreiv.twitter.domain;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
@Component
public class Tweet {
    private User user;
    private String text;
    private int likesCount;
    private int retweetsCount;
    private List<User> mentions;
    private List<String> replies;

    public Tweet() {
    }

    public Tweet(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int like() {
        return ++likesCount;
    }

    public int getRetweetsCount() {
        return retweetsCount;
    }

    public void setRetweetsCount(int retweetsCount) {
        this.retweetsCount = retweetsCount;
    }

    public Tweet retweet() {
        retweetsCount++;
        return this;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public void addMention(User user) {
        mentions.add(user);
    }

    public List<String> getReplies() {
        return replies;
    }

    public void setReplies(List<String> replies) {
        this.replies = replies;
    }

    public void reply(String reply) {
        replies.add(reply);
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "user=" + user +
                ", text='" + text + '\'' +
                '}';
    }
}
