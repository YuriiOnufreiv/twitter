package ua.onufreiv.twitter.web.app;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.onufreiv.twitter.domain.User;

/**
 * Created by Yurii_Onufreiv on 19-Apr-17.
 */
@ControllerAdvice(assignableTypes = {UserController.class})
public class UserControllerAdvice {

    @ModelAttribute(value = "user")
    public User unNamedUser() {
        System.out.println("unNamed");
        return new User("NoName");
    }
}
