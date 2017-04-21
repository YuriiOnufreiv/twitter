package ua.onufreiv.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.onufreiv.twitter.domain.User;
import ua.onufreiv.twitter.service.UserService;

/**
 * Created by Yurii_Onufreiv on 14-Apr-17.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String allTweetsHandle(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String user() {
        return "createUser";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);

        return "redirect:/web/user/users";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String userInfo() {
        return "createUser";
    }
}
