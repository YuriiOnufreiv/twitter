package ua.onufreiv.twitter.web.rest;

/**
 * Created by Yurii_Onufreiv on 28-Apr-17.
 */
public class ExceptionDescription {
    public final String name;
    public final String message;

    public ExceptionDescription(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
