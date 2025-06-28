package com.example.scanner.controller;

import com.example.scanner.model.Usuario;
import com.example.scanner.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "scanner/cadastro_usuario";
    }

    @PostMapping("/cadastrar")
    public String salvarCadastro(@ModelAttribute Usuario usuario) {
        usuarioService.salvar(usuario);
        return "redirect:/usuario/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        model.addAttribute("usuario", usuario);
        return "scanner/editar_usuario";
    }

    @PostMapping("/editar/{id}")
    public String salvarEdicaoUsuario(@PathVariable Integer id, @Valid Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            usuario.setId(id);
            return "scanner/editar_usuario";
        }
        usuarioService.salvar(usuario);
        return "redirect:/sistema/itens-usuarios";
    }

    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário excluído com sucesso!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir este usuário, pois há movimentações vinculadas a ele.");
        }
        return "redirect:/sistema/itens-usuarios";
    }
}
