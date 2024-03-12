package com.utad.Probando.model;

import jakarta.persistence.*;
import lombok.Data;


/*
* Tabla de mi BD, con todos sus atributos, el id se va a generar automaticamente
* */
@Data
@Entity
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Double ranking;

}
