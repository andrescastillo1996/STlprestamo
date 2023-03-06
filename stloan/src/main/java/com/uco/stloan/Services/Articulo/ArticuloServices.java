package com.uco.stloan.Services.Articulo;

import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.articulo.Articulo;
import com.uco.stloan.model.persona.Persona;

import java.util.List;

public interface ArticuloServices {

    public List<Articulo> findAll();
    public Articulo findById(String ref);
    public Articulo save(Articulo articulo);
    public void deleteById(String ref);
    public boolean partialUpdate(long id, String key, String value) throws NotFoundEx;

}
