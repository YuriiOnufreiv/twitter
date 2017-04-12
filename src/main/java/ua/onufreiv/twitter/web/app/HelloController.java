package ua.onufreiv.twitter.web.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yurii_Onufreiv on 12-Apr-17.
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public void handleRequest(HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("Spring MVC HelloController");
        httpServletResponse.getWriter().println("<h1>Spring MVC HelloController</h1>");
    }
}
