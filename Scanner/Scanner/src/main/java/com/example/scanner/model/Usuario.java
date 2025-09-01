package com.example.scanner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column(name = "codigo_barra", unique = true)
    private String codigoDeBarras;

    public Usuario() {}

    public Usuario(String nome, String codigoDeBarras) {
        this.nome = nome;
        this.codigoDeBarras = codigoDeBarras;
    }
}
