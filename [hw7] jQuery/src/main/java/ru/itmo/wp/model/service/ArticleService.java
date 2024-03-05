package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
    private final UserService userService = new UserService();

    public void validateArticle(Article article) throws ValidationException {

        if (Strings.isNullOrEmpty(article.getTitle())) {
            throw new ValidationException("Title is required");
        }

        if (!article.getTitle().matches("[ a-z]+")) {
            throw new ValidationException("Title can contain only lowercase Latin letters");
        }

        if (article.getTitle().length() > 255) {
            throw new ValidationException("Title can't be longer than 255 letters");
        }

        if (Strings.isNullOrEmpty(article.getText())) {
            throw new ValidationException("Text is required");
        }

        if (article.getText().length() < 4) {
            throw new ValidationException("Text can't be shorter than 4 characters");
        }

        if (article.getText().length() > 64000) {
            throw new ValidationException("Text can't be longer than 64000 characters");
        }

    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<UserArticle> findAll() {
        List<Article> articles = articleRepository.findAll();
        List<UserArticle> result = new ArrayList<>();

        for (Article article : articles) {
            User user = userService.find(article.getUserId());
            result.add(new UserArticle(user, article));
        }

        return result;
    }

    public List<Article> findAllByUserId(long userId) {
        return articleRepository.findAllByUserId(userId);
    }

    public Article find(long articleId) {
        return articleRepository.find(articleId);
    }

    public void changeHidden(long articleId, boolean articleHidden) {
        articleRepository.changeHidden(articleId, articleHidden);
    }
}
