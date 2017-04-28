package ua.onufreiv.twitter.web.rest;

/**
 * Created by Yurii_Onufreiv on 28-Apr-17.
 */
public class NoSuchTweetException extends RuntimeException {
    public NoSuchTweetException(String message) {
        super(message);
    }
}
