package com.uco.stloan.model.persona;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Person {

    private Long id;
    private String identification;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String rol;
    private String RFIDcode;


    public Person( ) {
    }

    public Person(String identification, String name, String surname, String email, String password, String phone, String address, String rol, String RFIDcode ) {
        this.identification = identification;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.rol = rol;
        this.RFIDcode = RFIDcode;
    }
}
