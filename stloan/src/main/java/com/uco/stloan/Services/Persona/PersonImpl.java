package com.uco.stloan.Services.Persona;

import com.uco.stloan.Repository.Persona.PersonRepositoryI;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.persona.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonImpl implements PersonService {

    @Autowired
    PersonRepositoryI personRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll ( ) {
        return personRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Person findById (String identification ) {
        return personRepository.findByIdentification(identification);
    }

    @Override
    @Transactional
    public Person save (Person person ) {
        return personRepository.save(person);
    }

    @Override
    @Transactional
    public void deleteById ( String identification ) {
        personRepository.deleteByIdentification(identification);
    }

    @Override
    public boolean partialUpdate(int id, String key, String value) throws NotFoundEx {
        Optional<Person> optional = personRepository.findById(String.valueOf(id));
        if (optional.isPresent()) {
            Person person = optional.get();

            if (key.equalsIgnoreCase("identification")) {
                person.setIdentification(value);
            }
            if (key.equalsIgnoreCase("name")) {
                person.setName(value);
            }
            if (key.equalsIgnoreCase("surname")) {
                person.setSurname(value);
            }
            if (key.equalsIgnoreCase("email")) {
                person.setEmail(value);
            }
            if (key.equalsIgnoreCase("password")) {
                person.setPassword(value);
            }
            if (key.equalsIgnoreCase("phone")) {
                person.setPhone(value);
            }
            if (key.equalsIgnoreCase("address")) {
                person.setAddress(value);
            }
            if (key.equalsIgnoreCase("rol")) {
                person.setRol(value);
            }
            if (key.equalsIgnoreCase("RFIDcode")) {
                person.setRFIDcode(value);
            }

            personRepository.save(person);
            return true;
        } else {
            throw new NotFoundEx("RESOURCE_NOT_FOUND");
        }
    }
}
