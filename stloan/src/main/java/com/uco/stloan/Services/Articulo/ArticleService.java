package com.uco.stloan.Services.Articulo;

import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.articulo.Article;

import java.util.List;

public interface ArticleService {

    public List<Article> findAll();
    public Article findById(String ref);
    public Article save(Article article);
    public void deleteById(String ref);
    public boolean partialUpdate(long id, String key, String value) throws NotFoundEx;

}
