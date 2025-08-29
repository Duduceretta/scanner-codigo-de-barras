package com.example.scanner.controller;

import com.example.scanner.model.Item;
import com.example.scanner.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("item", new Item());
        return "scanner/cadastro_item";
    }

    @PostMapping("/cadastrar")
    public String registrarNovoItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        itemService.salvar(item);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Item cadastrado com sucesso!");

        return "redirect:/item/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Integer id, Model model) {
        Item item = itemService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));
        model.addAttribute("item", item);
        return "scanner/editar_item";
    }

    @PostMapping("/editar/{id}")
    public String atualizarItem(@PathVariable Integer id, @Valid Item item, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            item.setId(id);
            return "scanner/editar_item";
        }

        itemService.salvar(item);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Item atualizado com sucesso!");

        return "redirect:/sistema/itens-usuarios";
    }

    @GetMapping("/excluir/{id}")
    public String excluirItem(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        itemService.excluir(id);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Item excluído com sucesso!");

        return "redirect:/sistema/itens-usuarios";
    }
}
