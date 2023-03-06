package com.uco.stloan.Repository.Persona;

import com.uco.stloan.model.articulo.Articulo;
import com.uco.stloan.model.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonaRepositorio extends JpaRepository<Persona, String> {
    @Query(value = "SELECT * FROM persona WHERE identificacion=?",nativeQuery = true)
    public Persona findByIdentification(String identificacion);

    @Query(value = "DELETE FROM persona WHERE identificacion=?;",nativeQuery = true)
    public void deleteByIdentification(String identificacion);
}
