package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.annotation.Json;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MyArticlesPage {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            request.getSession().setAttribute("message", "You need authorise to visit this page.");
            throw new RedirectException("/index");
        }

        view.put("articles", articleService.findAllByUserId(user.getId()));
        view.put("user", user);
    }

    @Json
    private void updateHidden(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        boolean articleHidden = request.getParameter("buttonText").equals("Show");
        System.out.println(articleHidden);
        articleService.changeHidden(Long.parseLong(request.getParameter("articleId")), articleHidden);
    }

}
