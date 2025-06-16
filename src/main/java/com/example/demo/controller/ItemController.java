package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("itens", itemService.listarTodos());
        return "itens/lista";
    }

    @GetMapping("/novo")
    public String mostrarFormCadastro(Item item) {
        return "itens/cadastro";
    }

    @PostMapping
    public String salvar(@Valid Item item, BindingResult result) {
        if (result.hasErrors()) {
            return "itens/cadastro";
        }
        itemService.salvar(item);
        return "redirect:/itens";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormEdicao(@PathVariable Integer id, Model model) {
        Item item = itemService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));
        model.addAttribute("item", item);
        return "itens/editar";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Integer id, @Valid Item item, BindingResult result) {
        if (result.hasErrors()) {
            item.setId(id);
            return "itens/editar";
        }
        itemService.salvar(item);
        return "redirect:/itens";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        itemService.excluir(id);
        return "redirect:/itens";
    }
}
