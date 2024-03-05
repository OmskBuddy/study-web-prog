package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {

    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @GetMapping({"/post/", "/post"})
    @Guest
    public String showPost(HttpSession httpSession) {
        return noSuchPost(httpSession);
    }

    @GetMapping("/post/{stringId}")
    @Guest
    public String showPost(@PathVariable("stringId") String stringId, Model model, HttpSession httpSession) {
        long id;
        try {
            id = Long.parseLong(stringId);
        } catch (NumberFormatException e) {
            return noSuchPost(httpSession);
        }

        Post post = postService.findById(id);

        if (post != null) {
            model.addAttribute("post", post);
            model.addAttribute("comment", new Comment());
            return "PostPage";
        } else {
            return noSuchPost(httpSession);
        }
    }

    @PostMapping("/post/{stringId}")
    @AnyRole({Role.Name.ADMIN, Role.Name.WRITER})
    public String writeComment(@PathVariable("stringId") String stringId,
                               @Valid @ModelAttribute("comment") Comment comment,
                               BindingResult bindingResult,
                               Model model,
                               HttpSession httpSession) {
        long id;
        try {
            id = Long.parseLong(stringId);
        } catch (NumberFormatException e) {
            return noSuchPost(httpSession);
        }

        model.addAttribute("post", postService.findById(id));

        if (bindingResult.hasErrors()) {
            return "PostPage";
        }

        postService.writeComment(getUser(httpSession), postService.findById(id), comment);
        putMessage(httpSession, "Comment was written!");

        return "redirect:/post/{stringId}";
    }

    private String noSuchPost(HttpSession httpSession) {
        putMessage(httpSession, "No such post");
        return "redirect:/";
    }

}
