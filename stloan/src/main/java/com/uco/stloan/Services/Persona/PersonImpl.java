package com.uco.stloan.Services.Persona;

import com.uco.stloan.Repository.PersonRepository;
import com.uco.stloan.dto.PersonDTO;
import com.uco.stloan.exception.GlobalExeptionHandler;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class PersonImpl implements PersonService {

    @Autowired
    public PersonService personService;

    private final PersonRepository personRepository;
    @Autowired
    public PersonImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll ( ) {
        return personRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Person findById (Long id ) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    @Transactional
    public void deleteById (Long id) {
        personRepository.findById(id);
    }


    @Override
    public boolean partialUpdate ( Long id, String key, String value ) throws NotFoundEx {
        Optional<Person> optional = personRepository.findById(id);
        if (optional.isPresent()) {
            Person person = optional.get();

            if (key.equalsIgnoreCase("identification")) {
                person.setIdentification(value);
            }
            if (key.equalsIgnoreCase("name")) {
                person.setName(value);
            }
            if (key.equalsIgnoreCase("lastname")) {
                person.setLastname(value);
            }
            if (key.equalsIgnoreCase("email")) {
                person.setEmail(value);
            }
            if (key.equalsIgnoreCase("password")) {
                person.setPassword(value);
            }
            if (key.equalsIgnoreCase("cellular")) {
                person.setMobile(value);
            }
            if (key.equalsIgnoreCase("address")) {
                person.setAddress(value);
            }
            if (key.equalsIgnoreCase("rol")) {
                person.setRol(value);
            }
            if (key.equalsIgnoreCase("codeRFID")) {
                person.setRFID(value);
            }

            personRepository.save(person);
            return true;
        } else {
            throw new NotFoundEx("RESOURCE_NOT_FOUND");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existByEmail(String email){
        try{
            PersonDTO person = new PersonDTO();
            boolean existEmail = personService.existByEmail(person.getEmail());
            if (existEmail){
                throw new GlobalExeptionHandler(HttpStatus.BAD_REQUEST);
            }

        }catch (DataAccessException e){

        }
        return personRepository.existByEmail(email);
    }

}
