package com.uco.stloan.model.persona;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Persona {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private String celular;
    private String direccion;
    private String rol;
    private String codigoRFID;


    public Persona ( ) {
    }

    public Persona ( String identificacion, String nombre, String apellido, String email, String contraseña, String celular, String direccion, String rol, String codigoRFID ) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contraseña = contraseña;
        this.celular = celular;
        this.direccion = direccion;
        this.rol = rol;
        this.codigoRFID = codigoRFID;
    }
}
