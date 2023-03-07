package com.uco.stloan.model.articulo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Article {

    private Long id;
    private String ref;
    private String name;
    private int quantity;

    public Article() {

    }

    public Article(String ref, String name, int quantity ) {
        this.ref = ref;
        this.name = name;
        this.quantity = quantity;
    }
}
