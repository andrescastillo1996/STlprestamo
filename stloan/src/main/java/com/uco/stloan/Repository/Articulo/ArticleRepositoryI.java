package com.uco.stloan.Repository.Articulo;

import com.uco.stloan.model.articulo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepositoryI extends JpaRepository<Article,String> {
    @Query(value = "SELECT * FROM articulo WHERE ref=?",nativeQuery = true)
    public Article findByRef(String ref);

    @Query(value = "DELETE FROM articulo WHERE ref=?;",nativeQuery = true)
    public void deleteByRef(String ref);
}
