package com.example.scanner.dto;

import com.example.scanner.model.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    private String id;
    private String nome;
    private String codigoDeBarras;
    private Item.TipoItem tipo;
    private Item.StatusItem status;
}
