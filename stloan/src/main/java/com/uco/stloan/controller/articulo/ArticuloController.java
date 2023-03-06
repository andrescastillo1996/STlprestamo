package com.uco.stloan.controller.articulo;


import com.uco.stloan.Services.Articulo.ArticuloServices;
import com.uco.stloan.dto.PatchDto;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.exception.NotYetImplementedEx;
import com.uco.stloan.model.articulo.Articulo;
import com.uco.stloan.model.persona.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rest/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloServices articleService;

    @GetMapping
    public ResponseEntity<?> listArticles() {
        List<Articulo> articles = articleService.findAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Articulo> create( @Valid @RequestBody Articulo articles) {
        return new ResponseEntity<>(articleService.save(articles), HttpStatus.OK);
    }

    @DeleteMapping
    public void delete ( @RequestParam(required = true) String ref ){
        articleService.deleteById (ref);
    }

    @PutMapping
    public ResponseEntity<Articulo> edit(@Valid @RequestBody Articulo article,
                                        @RequestParam(required = true) String ref ){

        Articulo articleDB = null;
        Articulo articleCurrent;

        articleDB = articleService.findById(ref);
        if(articleDB == null){
            return new ResponseEntity<>(articleService.findById(ref), HttpStatus.BAD_REQUEST);
        }
        articleCurrent = new Articulo(article.getRef(),article.getNombre(),article.getCantidad());

        //empleadoDB.setNombre(empleadoCurrent.getNombre());
        articleDB.setId(articleCurrent.getId());
        articleDB.setNombre(articleCurrent.getNombre());
        articleDB.setCantidad(articleCurrent.getCantidad());




        articleDB = articleService.save(articleDB);
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updatePartially(@PathVariable(name = "id") int id,
                                                   @RequestBody PatchDto dto) throws NotYetImplementedEx, NotFoundEx {
        // skipping validations for brevity
        if (dto.getOp().equalsIgnoreCase("update")) {
            boolean result = articleService.partialUpdate(id, dto.getKey(), dto.getValue());
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } else {
            throw new NotYetImplementedEx("NOT_YET_IMPLEMENTED");
        }
    }



}
