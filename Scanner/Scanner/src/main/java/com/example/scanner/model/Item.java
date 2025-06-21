package com.example.scanner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nome;

    // Precisa ter tipo?
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    @NotBlank
    @Column(name = "codigo_barra", unique = true)
    private String codigoBarra;

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

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public StatusItem getStatus() {
        return status;
    }

    public void setStatus(StatusItem status) {
        this.status = status;
    }
}
