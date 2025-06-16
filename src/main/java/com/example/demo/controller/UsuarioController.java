package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/lista";
    }

    @GetMapping("/novo")
    public String mostrarFormCadastro(Usuario usuario) {
        return "usuarios/cadastro";
    }

    @PostMapping
    public String salvar(@Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "usuarios/cadastro";
        }
        usuarioService.salvar(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormEdicao(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        model.addAttribute("usuario", usuario);
        return "usuarios/editar";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Integer id, @Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            usuario.setId(id);
            return "usuarios/editar";
        }
        usuarioService.salvar(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        usuarioService.excluir(id);
        return "redirect:/usuarios";
    }
}
