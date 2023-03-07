package com.uco.stloan.controller.articulo;


import com.uco.stloan.Services.Articulo.ArticleService;
import com.uco.stloan.dto.PatchDto;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.exception.NotYetImplementedEx;
import com.uco.stloan.model.articulo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rest/articles")
public class ArticleController {

    @Autowired
    private ArticleService serviceArticle;

    @GetMapping
    public ResponseEntity<?> listArticles() {
        List<Article> articles = serviceArticle.findAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Article> create(@Valid @RequestBody Article articles) {
        return new ResponseEntity<>(serviceArticle.save(articles), HttpStatus.OK);
    }

    @DeleteMapping
    public void delete ( @RequestParam(required = true) String ref ){
        serviceArticle.deleteById (ref);
    }

    @PutMapping
    public ResponseEntity<Article> edit(@Valid @RequestBody Article article,
                                        @RequestParam(required = true) String ref ){

        Article articleDB = null;
        Article articleCurrent;

        articleDB = serviceArticle.findById(ref);
        if(articleDB == null){
            return new ResponseEntity<>(serviceArticle.findById(ref), HttpStatus.BAD_REQUEST);
        }
        articleCurrent = new Article(article.getRef(),article.getName(),article.getQuantity());

        //empleadoDB.setName(empleadoCurrent.getName());
        articleDB.setId(articleCurrent.getId());
        articleDB.setName(articleCurrent.getName());
        articleDB.setQuantity(articleCurrent.getQuantity());



        articleDB = serviceArticle.save(articleDB);
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updatePartially(@PathVariable(name = "id") int id,
                                                   @RequestBody PatchDto dto) throws NotYetImplementedEx, NotFoundEx {
        // skipping validations for brevity
        if (dto.getOp().equalsIgnoreCase("update")) {
            boolean result = serviceArticle.partialUpdate(id, dto.getKey(), dto.getValue());
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } else {
            throw new NotYetImplementedEx("NOT_YET_IMPLEMENTED");
        }
    }

}
