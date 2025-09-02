package com.example.scanner.dto;

import com.example.scanner.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemListagemDTO {
    private int id;
    private String nome;
    private String codigoDeBarras;
    private Item.StatusItem status;
}
