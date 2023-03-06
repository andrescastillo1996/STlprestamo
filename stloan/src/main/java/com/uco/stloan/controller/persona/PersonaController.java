package com.uco.stloan.controller.persona;

import com.uco.stloan.Services.Persona.PersonaService;
import com.uco.stloan.dto.PatchDto;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.exception.NotYetImplementedEx;
import com.uco.stloan.model.persona.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/rest/personas")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public  ResponseEntity<?> listaPersonas() {
        List<Persona> personas = personaService.findAll();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Persona> create( @Valid @RequestBody Persona persona) {
        return new ResponseEntity<>(personaService.save(persona), HttpStatus.OK);
    }

    @DeleteMapping
    public void delete ( @RequestParam(required = true) String idPersona){
     personaService.deleteById (idPersona);
    }

    @PutMapping
    public ResponseEntity<Persona> edit(@Valid @RequestBody Persona persona,
                              @RequestParam(required = true) String idPersona ){

        Persona personaDB = null;
        Persona personCurrent;

        personaDB = personaService.findById(idPersona);
        if(personaDB == null){
            return new ResponseEntity<>(personaService.findById(idPersona), HttpStatus.BAD_REQUEST);
        }
        personCurrent = new Persona(persona.getIdentificacion(),
                persona.getNombre(),persona.getApellido(),
                persona.getEmail(),persona.getContraseña(),
                persona.getCelular(),persona.getDireccion(),
        persona.getRol(),persona.getCodigoRFID());

        //empleadoDB.setNombre(empleadoCurrent.getNombre());
        personaDB.setIdentificacion(personCurrent.getIdentificacion());
        personaDB.setNombre(personCurrent.getNombre());
        personaDB.setApellido(personCurrent.getApellido());
        personaDB.setEmail(personCurrent.getEmail());
        personaDB.setContraseña(personCurrent.getContraseña());
        personaDB.setCelular(personCurrent.getCelular());
        personaDB.setDireccion(personCurrent.getDireccion());
        personaDB.setRol(personCurrent.getRol());
        personaDB.setCodigoRFID(personCurrent.getCodigoRFID());




        personaDB = personaService.save(personaDB);
        return new ResponseEntity<>(persona, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updatePartially(@PathVariable(name = "id") int id,
                                                   @RequestBody PatchDto dto) throws NotYetImplementedEx, NotFoundEx {
        // skipping validations for brevity
        if (dto.getOp().equalsIgnoreCase("update")) {
            boolean result = personaService.partialUpdate(id, dto.getKey(), dto.getValue());
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } else {
            throw new NotYetImplementedEx("NOT_YET_IMPLEMENTED");
        }
    }


}
