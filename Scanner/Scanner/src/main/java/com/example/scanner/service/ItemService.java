package com.example.scanner.service;

import com.example.scanner.dto.ItemDTO;
import com.example.scanner.exception.ItemJaCadastradoException;
import com.example.scanner.model.Item;
import com.example.scanner.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> listarTodos() {
        return itemRepository.findAll();
    }

    public Optional<Item> buscarPorId(Integer id) {
        return itemRepository.findById(id);
    }

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

    public void excluir(Integer id) {
        itemRepository.deleteById(id);
    }

    public Optional<Item> buscarPorCodigoBarra(String codigo) {
        return itemRepository.findByCodigoDeBarras(codigo);
    }
}
