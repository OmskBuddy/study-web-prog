package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.form.TagsString;
import ru.itmo.wp.form.validator.TagsStringValidator;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.service.TagService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Set;

@Controller
public class WritePostPage extends Page {
    private final UserService userService;
    private final TagService tagService;
    private final TagsStringValidator tagsStringValidator;

    public WritePostPage(UserService userService, TagService tagService, TagsStringValidator tagsStringValidator) {
        this.userService = userService;
        this.tagService = tagService;
        this.tagsStringValidator = tagsStringValidator;
    }

    @AnyRole({Role.Name.WRITER, Role.Name.ADMIN})
    @GetMapping("/writePost")
    public String writePostGet(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("tagsWrap", new TagsString());
        return "WritePostPage";
    }

    @AnyRole({Role.Name.WRITER, Role.Name.ADMIN})
    @PostMapping("/writePost")
    public String writePostPost(@Valid @ModelAttribute("post") Post post,
                                BindingResult bindingResult1,
                                @Valid @ModelAttribute("tagsWrap") TagsString tagsWrap,
                                BindingResult bindingResult2,
                                HttpSession httpSession) {

        if (bindingResult1.hasErrors() || bindingResult2.hasErrors()) {
            return "WritePostPage";
        }

        Set<Tag> tagSet = tagService.getSetOfTags(tagsWrap.getTagNames());
        for (Tag tag : tagSet) {
            if (!tagService.contains(tag)) {
                tagService.save(tag);
            }
            post.addTag(tag);
        }
        
        userService.writePost(getUser(httpSession), post);
        putMessage(httpSession, "You published new post");

        return "redirect:/myPosts";
    }
}
