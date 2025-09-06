package com.example.scanner.service;

import com.example.scanner.dto.ItemDTO;
import com.example.scanner.dto.ItemListagemDTO;
import com.example.scanner.dto.ItemUpdateDTO;
import com.example.scanner.exception.ItemJaCadastradoException;
import com.example.scanner.model.Item;
import com.example.scanner.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void registrarNovoItem(ItemDTO itemDTO) {
        verificarSeOItemJaEstaRegistrado(itemDTO.getCodigoDeBarras());

        Item item = new Item(
                itemDTO.getNome(),
                itemDTO.getTipo(),
                itemDTO.getCodigoDeBarras(),
                Item.StatusItem.disponivel
        );

        itemRepository.save(item);
    }

    private void verificarSeOItemJaEstaRegistrado(String codigoDeBarras) {
        if (itemRepository.findByCodigoDeBarras(codigoDeBarras).isPresent()) {
            throw new ItemJaCadastradoException("Ja existe um Item cadastrado com o mesmo codigo de barras no sistema.");
        }
    }

    public void atualizarItem(int id,  ItemUpdateDTO itemDTO) {
        Item itemExistente = this.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));

        itemExistente.setNome(itemDTO.getNome());
        itemExistente.setTipo(itemDTO.getTipo());
        itemExistente.setCodigoDeBarras(itemDTO.getCodigoDeBarras());
        itemExistente.setStatus(itemDTO.getStatus());

        itemRepository.save(itemExistente);
    }

    public List<ItemListagemDTO> listarTodosParaVisualizacao() {
        List<Item> itens = itemRepository.findAll();

        return itens.stream()
                .map(item -> new ItemListagemDTO(
                        item.getId(),
                        item.getNome(),
                        item.getCodigoDeBarras(),
                        item.getStatus()
                ))
                .collect(Collectors.toList());
    }

    public List<Item> listarTodos() {
        return itemRepository.findAll();
    }

    public Optional<Item> buscarPorId(Integer id) {
        return itemRepository.findById(id);
    }

    public void excluir(Integer id) {
        itemRepository.deleteById(id);
    }

    public Optional<Item> buscarPorCodigoBarra(String codigo) {
        return itemRepository.findByCodigoDeBarras(codigo);
    }
}
