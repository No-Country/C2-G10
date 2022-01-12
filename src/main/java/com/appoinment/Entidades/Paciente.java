package com.appoinment.Entidades;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//ENTIDAD
@Entity
// CREA LA TABLA CORRESPONDIENTE "pacientes" EN LA BASE DE DATOS - MySQL
@Table(name = "paciente")
public class Paciente {


    @Id
    //REFERENCIA AL TABLA PACIENTE
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //ATRIBUTOS AND GETTER, SETTERS
    @Getter  @Setter
    private int id;

    @Getter  @Setter
    private String nombre;

    @Getter  @Setter
    private String email;

    @Getter  @Setter
    private int dni;

    @Getter  @Setter
    private String password;

    //METODOS , CONTROCTURES

    public Paciente(int id, String nombre, String email, int dni, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.password = password;
    }

    public Paciente() {
    }
}
