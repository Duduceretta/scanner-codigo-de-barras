package com.example.scanner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    @NotBlank
    @Column(name = "codigo_barra", unique = true)
    private String codigoDeBarras;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusItem status = StatusItem.disponivel;

    public enum TipoItem {
        chave,
        controle
    }

    public enum StatusItem {
        disponivel,
        emprestado
    }

    public Item() {}

    public Item(String nome, TipoItem tipo, String codigoDeBarras, StatusItem status) {
        this.nome = nome;
        this.tipo = tipo;
        this.codigoDeBarras = codigoDeBarras;
        this.status = status;
    }

}
