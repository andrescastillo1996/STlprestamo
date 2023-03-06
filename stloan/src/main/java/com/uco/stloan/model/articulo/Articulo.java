package com.uco.stloan.model.articulo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Articles")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ref;
    private String nombre;
    private int cantidad;

    public Articulo() {

    }

    public Articulo (String ref, String nombre, int cantidad ) {
        this.ref = ref;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
}
