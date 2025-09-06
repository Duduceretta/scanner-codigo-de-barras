package com.example.scanner.dto;

import com.example.scanner.model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemUpdateDTO {
    private String nome;
    private String codigoDeBarras;
    private Item.TipoItem tipo;
    private Item.StatusItem status;

    public ItemUpdateDTO(Item item) {
        this.nome = item.getNome();
        this.codigoDeBarras = item.getCodigoDeBarras();
        this.tipo = item.getTipo();
        this.status = item.getStatus();
    }
}
