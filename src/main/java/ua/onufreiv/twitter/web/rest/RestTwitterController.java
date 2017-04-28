package ua.onufreiv.twitter.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.service.TweetService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

    @RequestMapping(value = "/tweetht/{tweetid}", method = RequestMethod.GET, produces = "application/json")
    public Tweet tweetht(@PathVariable("tweetid") Long id) {
        return tweetService.findById(id).get();
    }

    @RequestMapping(value = "/newtweet",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<Void> newTweet(@RequestBody Tweet tweet, UriComponentsBuilder builder) {
        System.out.println(tweet);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/tweet/{tweetID}").buildAndExpand(4).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "tweetht",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<Tweet> newTweetHATEOAS(@RequestBody Tweet tweet) {
        System.out.println(tweet);

        Link link = linkTo(methodOn(RestTwitterController.class).tweetht(tweet.getTweetId())).withSelfRel();
        tweet.add(link);

        return new ResponseEntity<>(tweet, HttpStatus.CREATED);
    }
}
