package ua.onufreiv.twitter.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.service.TweetService;

/**
 * Created by Yurii_Onufreiv on 28-Apr-17.
 */
@RestController
public class RestTwitterController {
    @Autowired
    private TweetService tweetService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
    public String[] helloRest() {
        return new String[]{"Hello from REST"};
    }

    @RequestMapping(value = "/tweets", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Tweet> allTweets() {
        return tweetService.findAll();
    }

    @RequestMapping(value = "/tweet/{tweetid}", method = RequestMethod.GET, produces = "application/json")
    public Tweet tweet(@PathVariable("tweetid") Tweet tweet) {
        return tweet;
    }

    @RequestMapping(value = "/newtweet", method = RequestMethod.POST, produces = "application/json",
            consumes = "application/json")
    public Tweet newTweet(@RequestBody Tweet tweet) {
        System.out.println(tweet);
        return tweet;
    }
}
