package com.uco.stloan.Repository.Persona;

import com.uco.stloan.model.persona.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepositoryI extends JpaRepository<Person, String> {
    @Query(value = "SELECT * FROM persona WHERE identificacion=?",nativeQuery = true)
    public Person findByIdentification(String identificacion);

    @Query(value = "DELETE FROM persona WHERE identificacion=?;",nativeQuery = true)
    public void deleteByIdentification(String identificacion);
}
