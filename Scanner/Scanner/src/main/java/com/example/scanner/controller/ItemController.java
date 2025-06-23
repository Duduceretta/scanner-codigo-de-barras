package com.example.scanner.controller;

import com.example.scanner.model.Item;
import com.example.scanner.model.Usuario;
import com.example.scanner.service.ItemService;
import com.example.scanner.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    //Mostra o cadastro de item para o porteiro
    @GetMapping("/cadastro")
    public String mostrarFormularioItem(Model model) {
        model.addAttribute("item", new Item());
        return "scanner/cadastro_item";
    }

    //Envia o formulario preenchido para o banco de dados e redireciona para o cadastro de itens
    @PostMapping("/cadastrar")
    public String salvarCadastroItem(@ModelAttribute Item item) {
        itemService.salvar(item);
        return "redirect:/item/cadastro";
    }

    @PostMapping("/emprestar/{id}")
    @ResponseBody
    public ResponseEntity<String> emprestarItem(@PathVariable Integer id) {
        try {
            itemService.atualizarStatus(id, Item.StatusItem.emprestado);
            return ResponseEntity.ok("Item emprestado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/devolver/{id}")
    @ResponseBody
    public ResponseEntity<String> devolverItem(@PathVariable Integer id) {
        try {
            itemService.atualizarStatus(id, Item.StatusItem.disponivel);
            return ResponseEntity.ok("Item devolvido com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/status/{codigoBarra}")
    @ResponseBody
    public ResponseEntity<String> obterStatusItem(@PathVariable String codigoBarra) {
        Optional<Item> itemOpt = itemService.buscarPorCodigoBarra(codigoBarra);

        if (itemOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado");
        }

        Item item = itemOpt.get();
        return ResponseEntity.ok(item.getStatus().name()); // Retorna "disponivel" ou "emprestado"
    }


    /*
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("itens", itemService.listarTodos());
        return "itens/lista";
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
    } */

}
