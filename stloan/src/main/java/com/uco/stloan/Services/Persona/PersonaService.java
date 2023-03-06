package com.uco.stloan.Services.Persona;

import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.persona.Persona;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonaService {

    public List<Persona> findAll();
    public Persona findById(String identificacion);
    public Persona save(Persona persona);
    public void deleteById(String identificacion);
    public boolean partialUpdate(int id, String key, String value) throws NotFoundEx;


}
