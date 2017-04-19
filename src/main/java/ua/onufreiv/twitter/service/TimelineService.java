package ua.onufreiv.twitter.service;

import ua.onufreiv.twitter.domain.Timeline;
import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.domain.User;

import java.util.Optional;

/**
 * Created by Yurii_Onufreiv on 31-Mar-17.
 */
public interface TimelineService {
    Optional<Tweet> getTweetById(Long id);

    Tweet createTweet(User user, String text);

    Tweet makeReTweet(User user, String text, Tweet reTweeted);

    Tweet replyToTweet(User user, String text, Tweet reTweeted);

    void likeTweet(Tweet tweet, User likedUser);

    Timeline getHomeTimeline(User user);

    Timeline getUserTimeline(User user);

    Iterable<Tweet> tweets();
}
