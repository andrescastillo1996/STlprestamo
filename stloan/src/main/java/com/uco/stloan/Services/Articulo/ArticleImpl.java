package com.uco.stloan.Services.Articulo;

import com.uco.stloan.Repository.Articulo.ArticleRepositoryI;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.articulo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleImpl implements ArticleService {

    @Autowired
    ArticleRepositoryI repositoryArticle;

    @Override
    public List<Article> findAll ( ) {
        return repositoryArticle.findAll();
    }

    @Override
    public Article findById (String ref ) {
        return repositoryArticle.findByRef(ref);
    }

    @Override
    public Article save (Article articulo ) {
        return repositoryArticle.save(articulo);
    }

    @Override
    public void deleteById ( String ref ) {
        repositoryArticle.deleteByRef(ref);
    }

    @Override
    public boolean partialUpdate(long id, String key, String value) throws NotFoundEx {
        Optional<Article> optional = repositoryArticle.findById(String.valueOf(id));
        if (optional.isPresent()) {
            Article article = optional.get();

            if (key.equalsIgnoreCase("ref")) {
                article.setRef(value);
            }
            if (key.equalsIgnoreCase("name")) {
                article.setName(value);
            }
            if (key.equalsIgnoreCase("quantity")) {
                article.setQuantity(Integer.parseInt(value));
            }

            repositoryArticle.save(article);
            return true;
        } else {
            throw new NotFoundEx("RESOURCE_NOT_FOUND");
        }
    }
}
