package com.example.scanner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioListagemDTO {
    private int id;
    private String nome;
    private String codigoDeBarras;
}
