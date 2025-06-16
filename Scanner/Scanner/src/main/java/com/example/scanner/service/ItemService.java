package com.example.scanner.service;

import com.example.scanner.model.Item;
import com.example.scanner.repository.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemDAO itemDAO;

    public List<Item> listarTodos() {
        return itemDAO.findAll();
    }

    public Optional<Item> buscarPorId(Integer id) {
        return itemDAO.findById(id);
    }

    public Item salvar(Item item) {
        return itemDAO.save(item);
    }

    public void excluir(Integer id) {
        itemDAO.deleteById(id);
    }
}
