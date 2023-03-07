package com.uco.stloan.Repository.Persona;

import com.uco.stloan.model.persona.Person;

public class PersonRepository extends Person {
    public PersonRepository(String identificacion, String nombre, String apellido, String email, String contraseña, String celular, String direccion, String rol, String codigoRFID ) {
        super(identificacion, nombre, apellido, email, contraseña, celular, direccion, rol, codigoRFID);
    }
}
