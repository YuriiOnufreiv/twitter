package ua.onufreiv.twitter.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ua.onufreiv.twitter.domain.User;
import ua.onufreiv.twitter.service.UserService;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

/**
 * Created by Yurii_Onufreiv on 19-Apr-17.
 */
@ControllerAdvice(assignableTypes = {UserController.class})
public class UserControllerAdvice {
    private final UserService userService;

    @Autowired
    public UserControllerAdvice(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute(value = "user")
    public User unNamedUser(@RequestParam(value = "id", required = false) User user) {
        return user;
    }

    @InitBinder
    public void userAttribute(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text != null && !text.isEmpty()) {
                    Long id = Long.valueOf(text);
                    System.out.println("id " + id);
                    Optional<User> user = userService.findById(id);
                    setValue(user.get());
                } else {
                    setValue(new User());
                }
            }
        });
    }
}
