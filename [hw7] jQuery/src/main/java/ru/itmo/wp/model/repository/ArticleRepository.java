package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.service.UserArticle;

import java.util.List;

public interface ArticleRepository {

    void save(Article article);

    Article find(long id);

    List<Article> findAll();

    List<Article> findAllByUserId(long userId);

    void changeHidden(long articleId, boolean articleHidden);
}
