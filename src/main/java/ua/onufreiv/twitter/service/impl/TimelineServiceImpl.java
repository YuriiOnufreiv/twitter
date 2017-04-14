package ua.onufreiv.twitter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import ua.onufreiv.twitter.domain.Timeline;
import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.domain.User;
import ua.onufreiv.twitter.repository.TweetRepository;
import ua.onufreiv.twitter.service.TimelineService;

/**
 * Created by Yurii_Onufreiv on 14-Apr-17.
 */
@Component("timelineService")
public class TimelineServiceImpl implements TimelineService {
    private TweetRepository<Tweet> tweetRepository;

    @Autowired
    public TimelineServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Tweet getTweetById(int id) {
        return tweetRepository.findByID(id);
    }

    @Override
    public Tweet createTweet(User user, String text) {
        Tweet tweet = getTweetBean();
        tweet.setUser(user);
        tweet.setText(text);
        tweetRepository.create(tweet);
        return tweet;
    }

    @Override
    public Tweet makeReTweet(User user, String text, Tweet reTweeted) {
        Tweet tweet = createTweet(user, text);
        tweet.setReTweetedTweet(reTweeted);
        return tweet;
    }

    @Override
    public Tweet replyToTweet(User user, String text, Tweet reTweeted) {
        Tweet tweet = createTweet(user, text);
        tweet.setInReplyToTweet(reTweeted);
        return tweet;
    }

    @Override
    public void likeTweet(Tweet tweet, User likedUser) {
        tweet.getLikes().add(likedUser);
    }

    // TODO: 14-Apr-17
    @Override
    public Timeline getHomeTimeline(User user) {
        return null;
    }

    @Override
    public Timeline getUserTimeline(User user) {
        Timeline timeline = getTimelineBean();
        timeline.addTweet(tweetRepository.findUserTweets(user));
        return timeline;
    }

    @Override
    public Iterable<Tweet> tweets() {
        return tweetRepository.findAll();
    }

    @Lookup("timeline")
    private Timeline getTimelineBean() {
        return null;
    }

    @Lookup("tweet")
    private Tweet getTweetBean() {
        return null;
    }
}
