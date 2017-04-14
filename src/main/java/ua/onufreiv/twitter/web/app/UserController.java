package ua.onufreiv.twitter.web.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.onufreiv.twitter.domain.User;

/**
 * Created by Yurii_Onufreiv on 14-Apr-17.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String user() {
        return "createUser";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody String createUser(@ModelAttribute("user") User user) {
        System.out.println(user);
        return user.toString();
    }

//    @ModelAttribute(value = "user")
//    public User unNamedUser() {
//        System.out.println("unNamed");
//        return new User("NoName");
//    }

}
