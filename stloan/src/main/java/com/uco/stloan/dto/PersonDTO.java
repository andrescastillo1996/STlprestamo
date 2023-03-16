package com.uco.stloan.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class PersonDTO {

    @NotNull
    @NotBlank
    private String identification;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String lastname;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 15)
    private String mobile;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @NotBlank
    private String rol;

    @NotNull
    @NotBlank
    private String RFID;

}
