package ua.onufreiv.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.onufreiv.twitter.domain.Tweet;
import ua.onufreiv.twitter.service.TimelineService;
import ua.onufreiv.twitter.service.TweetService;

/**
 * Created by Yurii_Onufreiv on 12-Apr-17.
 */
@Controller
public class TweetsController {
    private final TimelineService timelineService;
    private final TweetService tweetService;

    @Autowired
    public TweetsController(TimelineService timelineService, TweetService tweetService) {
        this.timelineService = timelineService;
        this.tweetService = tweetService;
    }

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
    public String allTweetsHandle(Model model) {
        model.addAttribute("tweets", timelineService.tweets());
        return "tweets";
    }

    @RequestMapping(value = "/tweet", method = RequestMethod.GET)
    @ResponseBody
    public String tweetById(@RequestParam("id") Tweet tweet) {
        return tweet.toString();
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    @ResponseBody
    public String modify(@RequestParam("id") Long id) {
        return tweetService.findById(id).map(Tweet::toString).orElse("");
    }

//    @InitBinder
//    public void tweetBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Tweet.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                Long id = Long.valueOf(text);
//                Optional<Tweet> tweet = tweetService.findById(id);
//                setValue(tweet.orElse(null));
//            }
//        });
//    }

//    @RequestMapping(value="/tweets", method = RequestMethod.GET)
//    public String handleRequest(HttpServletResponse httpServletResponse) throws Exception {
//        httpServletResponse.getWriter().println("<h1>Spring MVC TweetsController</h1>");
//        httpServletResponse.getWriter().println("<h3>All tweets</h3>");
//        model.addAttribute("tweets", tweetService.findAllForUser(id));
//        return "mainTwitterPage";
////        for (Tweet tweet : timelineService.tweets()) {
////            httpServletResponse.getWriter().println("<p>" + tweet + "</p>");
////        }
//    }

}
