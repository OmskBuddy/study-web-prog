package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserPage extends Page {

    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/user", "/user/"})
    public String userProfile(HttpSession httpSession) {
        return noSuchUser(httpSession);
    }

    @GetMapping("/user/{stringId}")
    public String userProfile(@PathVariable("stringId") String stringId, Model model, HttpSession httpSession) {
        long id;
        try {
            id = Long.parseLong(stringId);
        } catch (NumberFormatException e) {
            return noSuchUser(httpSession);
        }

        User user = userService.findById(id);

        if (user != null) {
            model.addAttribute("user", user);
            return "UserPage";
        } else {
            return noSuchUser(httpSession);
        }
    }

    private String noSuchUser(HttpSession httpSession) {
        setMessage(httpSession, "No such user!");
        return "redirect:/";
    }

}
