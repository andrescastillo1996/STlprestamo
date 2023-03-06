package com.uco.stloan.Services.Persona;

import com.uco.stloan.Repository.Persona.PersonaRepositorio;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.persona.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaImpl implements PersonaService {

    @Autowired
    PersonaRepositorio personaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findAll ( ) {
        return personaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Persona findById ( String identificacion ) {
        return personaRepository.findByIdentification(identificacion);
    }

    @Override
    @Transactional
    public Persona save ( Persona persona ) {
        return personaRepository.save(persona);
    }

    @Override
    @Transactional
    public void deleteById ( String identificacion ) {
        personaRepository.deleteByIdentification(identificacion);
    }

    @Override
    public boolean partialUpdate(int id, String key, String value) throws NotFoundEx {
        Optional<Persona> optional = personaRepository.findById(String.valueOf(id));
        if (optional.isPresent()) {
            Persona persona = optional.get();

            if (key.equalsIgnoreCase("identificacion")) {
                persona.setIdentificacion(value);
            }
            if (key.equalsIgnoreCase("nombre")) {
                persona.setNombre(value);
            }
            if (key.equalsIgnoreCase("apellido")) {
                persona.setApellido(value);
            }
            if (key.equalsIgnoreCase("email")) {
                persona.setEmail(value);
            }
            if (key.equalsIgnoreCase("contraseña")) {
                persona.setContraseña(value);
            }
            if (key.equalsIgnoreCase("celular")) {
                persona.setCelular(value);
            }
            if (key.equalsIgnoreCase("direccion")) {
                persona.setDireccion(value);
            }
            if (key.equalsIgnoreCase("rol")) {
                persona.setRol(value);
            }
            if (key.equalsIgnoreCase("codigoRFID")) {
                persona.setCodigoRFID(value);
            }

            personaRepository.save(persona);
            return true;
        } else {
            throw new NotFoundEx("RESOURCE_NOT_FOUND");
        }
    }
}
