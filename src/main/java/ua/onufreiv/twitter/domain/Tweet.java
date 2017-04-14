package ua.onufreiv.twitter.domain;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Yurii_Onufreiv on 30-Mar-17.
 */
@Component
public class Tweet {
    private int id;
    private User user;
    private String text;
    private List<User> mentions;
    private List<Tweet> replies;
    private List<User> likes;
    private List<Tweet> reTweets;

    private Tweet inReplyToTweet;
    private Tweet reTweetedTweet;

    public Tweet() {
    }

    public Tweet(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<User> getMentions() {
        return mentions;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public List<Tweet> getReplies() {
        return replies;
    }

    public void setReplies(List<Tweet> replies) {
        this.replies = replies;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public List<Tweet> getReTweets() {
        return reTweets;
    }

    public void setReTweets(List<Tweet> reTweets) {
        this.reTweets = reTweets;
    }

    public Tweet getInReplyToTweet() {
        return inReplyToTweet;
    }

    public void setInReplyToTweet(Tweet inReplyToTweet) {
        this.inReplyToTweet = inReplyToTweet;
    }

    public Tweet getReTweetedTweet() {
        return reTweetedTweet;
    }

    public void setReTweetedTweet(Tweet reTweetedTweet) {
        this.reTweetedTweet = reTweetedTweet;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "user=" + user +
                ", text='" + text + '\'' +
                '}';
    }
}
