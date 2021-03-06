package ua.onufreiv.twitter.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.service.TweetService;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

/**
 * Created by Yurii_Onufreiv on 28-Apr-17.
 */
@RestControllerAdvice(assignableTypes = {RestTwitterController.class})
public class TweetControllerAdvice {
    @Autowired
    private TweetService tweetService;

    @InitBinder
    public void tweetBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Tweet.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Long id = Long.valueOf(text);
                Optional<Tweet> tweet = tweetService.findById(id);
                setValue(tweet.orElseThrow(() -> new NoSuchTweetException(id.toString())));
            }
        });
    }

    @ExceptionHandler(NoSuchTweetException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDescription onException(Exception ex) {
        return new ExceptionDescription("error", ex.getMessage() + " not found");
    }
}