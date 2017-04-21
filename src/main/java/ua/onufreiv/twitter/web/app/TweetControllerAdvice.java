package ua.onufreiv.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.service.TweetService;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

/**
 * Created by Yurii_Onufreiv on 19-Apr-17.
 */
@ControllerAdvice(assignableTypes = {TweetsController.class})
public class TweetControllerAdvice {
    private final TweetService tweetService;

    @Autowired
    public TweetControllerAdvice(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @InitBinder
    public void tweetBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Tweet.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                Long id = Long.valueOf(text);
                Optional<Tweet> tweet = tweetService.findById(id);
                setValue(tweet.orElse(null));
            }
        });
    }

//    @ExceptionHandler(UnsupportedOperationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String onException(Exception ex, Model model) {
//        model.addAttribute("err", ex.getStackTrace());
//        return "error";
//    }
}
