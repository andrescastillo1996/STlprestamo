package com.uco.stloan.Services.Articulo;

import com.uco.stloan.Repository.Articulo.ArticuloRepositorio;
import com.uco.stloan.Repository.Articulo.ArticuloRepository;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.articulo.Articulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticulosImpl implements ArticuloServices {

    @Autowired
    ArticuloRepositorio articuloRepository;

    @Override
    public List<Articulo> findAll ( ) {
        return articuloRepository.findAll();
    }

    @Override
    public Articulo findById ( String ref ) {
        return articuloRepository.findByRef(ref);
    }

    @Override
    public Articulo save ( Articulo articulo ) {
        return articuloRepository.save(articulo);
    }

    @Override
    public void deleteById ( String ref ) {
        articuloRepository.deleteByRef(ref);
    }

    @Override
    public boolean partialUpdate(long id, String key, String value) throws NotFoundEx {
        Optional<Articulo> optional = articuloRepository.findById(String.valueOf(id));
        if (optional.isPresent()) {
            Articulo articulo = optional.get();

            if (key.equalsIgnoreCase("ref")) {
                articulo.setRef(value);
            }
            if (key.equalsIgnoreCase("nombre")) {
                articulo.setNombre(value);
            }
            if (key.equalsIgnoreCase("cantidad")) {
                articulo.setCantidad(Integer.parseInt(value));
            }

            articuloRepository.save(articulo);
            return true;
        } else {
            throw new NotFoundEx("RESOURCE_NOT_FOUND");
        }
    }
}
