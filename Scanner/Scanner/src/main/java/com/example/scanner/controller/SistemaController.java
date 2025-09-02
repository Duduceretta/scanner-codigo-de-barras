package com.example.scanner.controller;

import com.example.scanner.service.ItemService;
import com.example.scanner.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SistemaController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/sistema")
    public String mostrarFormularioEmprestimo() {
        return "scanner/emprestimo_itens";
    }

    @GetMapping("/sistema/itens-usuarios")
    public String mostrarFormularioUsuariosItens(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodosParaVisualizacao());
        model.addAttribute("itens", itemService.listarTodosParaVisualizacao());
        return "scanner/usuarios_itens_cadastrados";
    }
}
