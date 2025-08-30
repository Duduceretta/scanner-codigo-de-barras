package com.example.scanner.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroDTO {
    private String nome;
    private String email;
    private String senha;
    private String senhaConfirmacao;
}
