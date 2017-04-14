package ua.onufreiv.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.onufreiv.twitter.service.TimelineService;

/**
 * Created by Yurii_Onufreiv on 12-Apr-17.
 */
@Controller
public class TweetsController {
    private TimelineService timelineService;

    @Autowired
    public TweetsController(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
    public String allTweetsHandle(Model model){
        model.addAttribute("tweets", timelineService.tweets());
        return "tweets";
    }

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
