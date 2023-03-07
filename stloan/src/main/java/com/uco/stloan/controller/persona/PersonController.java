package com.uco.stloan.controller.persona;

import com.uco.stloan.Services.Persona.PersonService;
import com.uco.stloan.dto.PatchDto;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.exception.NotYetImplementedEx;
import com.uco.stloan.model.persona.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rest/people")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public  ResponseEntity<?> listPerson() {
        List<Person> people = personService.findAll();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person persona) {
        return new ResponseEntity<>(personService.save(persona), HttpStatus.OK);
    }

    @DeleteMapping
    public void delete ( @RequestParam(required = true) String idPersona){
        personService.deleteById (idPersona);
    }

    @PutMapping
    public ResponseEntity<Person> edit(@Valid @RequestBody Person persona,
                                       @RequestParam(required = true) String idPersona ){

        Person personaDB = null;
        Person currentPerson;

        personaDB = personService.findById(idPersona);
        if(personaDB == null){
            return new ResponseEntity<>(personService.findById(idPersona), HttpStatus.BAD_REQUEST);
        }
        currentPerson = new Person(persona.getIdentification(),
                persona.getName(),persona.getSurname(),
                persona.getEmail(),persona.getPassword(),
                persona.getPhone(),persona.getAddress(),
        persona.getRol(),persona.getRFIDcode());

        //empleadoDB.setName(empleadoCurrent.getName());
        personaDB.setIdentification(currentPerson.getIdentification());
        personaDB.setName(currentPerson.getName());
        personaDB.setSurname(currentPerson.getSurname());
        personaDB.setEmail(currentPerson.getEmail());
        personaDB.setPassword(currentPerson.getPassword());
        personaDB.setPhone(currentPerson.getPhone());
        personaDB.setAddress(currentPerson.getAddress());
        personaDB.setRol(currentPerson.getRol());
        personaDB.setRFIDcode(currentPerson.getRFIDcode());




        personaDB = personService.save(personaDB);
        return new ResponseEntity<>(persona, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updatePartially(@PathVariable(name = "id") int id,
                                                   @RequestBody PatchDto dto) throws NotYetImplementedEx, NotFoundEx {
        // skipping validations for brevity
        if (dto.getOp().equalsIgnoreCase("update")) {
            boolean result = personService.partialUpdate(id, dto.getKey(), dto.getValue());
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } else {
            throw new NotYetImplementedEx("NOT_YET_IMPLEMENTED");
        }
    }


}
