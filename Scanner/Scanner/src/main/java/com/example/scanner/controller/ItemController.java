package com.example.scanner.controller;

import com.example.scanner.model.Item;
import com.example.scanner.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/cadastro")
    public String mostrarFormularioItem(Model model) {
        model.addAttribute("item", new Item());
        return "scanner/cadastro_item";
    }

    @PostMapping("/cadastrar")
    public String salvarCadastroItem(@ModelAttribute Item item) {
        itemService.salvar(item);
        return "redirect:/item/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormEdicao(@PathVariable Integer id, Model model) {
        Item item = itemService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));
        model.addAttribute("item", item);
        return "scanner/editar_item";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Integer id, @Valid Item item, BindingResult result) {
        if (result.hasErrors()) {
            item.setId(id);
            return "scanner/editar_item";
        }

        itemService.salvar(item);
        return "redirect:/sistema/itens-usuarios";
    }

    @GetMapping("/listar-emprestados-e-disponiveis")
    public String mostrarFormularioItensDisp(Model model) {
        model.addAttribute("item", new Item());
        return "scanner/itens_emprestados";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        itemService.excluir(id);
        return "redirect:/sistema/itens-usuarios";
    }
}
