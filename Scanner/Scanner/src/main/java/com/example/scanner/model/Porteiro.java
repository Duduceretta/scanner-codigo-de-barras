package com.example.scanner.model;

import com.example.scanner.exception.AutenticacaoException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Porteiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String senha;

    public Porteiro() {}

    public Porteiro(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void validarSenha(String senhaFornecida) {
        if (!this.senha.equals(senhaFornecida)) {
            throw new AutenticacaoException("Email ou senha inválidos");
        }
    }

}