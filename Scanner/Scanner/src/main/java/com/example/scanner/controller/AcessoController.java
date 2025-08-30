package com.example.scanner.controller;

import com.example.scanner.dto.LoginDTO;
import com.example.scanner.dto.RegistroDTO;
import com.example.scanner.model.Porteiro;
import com.example.scanner.service.PorteiroService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class AcessoController {

    @Autowired
    private PorteiroService porteiroService;

    // Redireciona para a página de login
    @GetMapping("/")
    public String redirecionarParaLogin() {
        return "redirect:/login";
    }

    // Página de login do porteiro no sistema
    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("porteiro", new Porteiro());
        return "acesso/login";
    }

    // Verifica se o login esta correto, e entra no sistema
    @PostMapping("/login")
    public String autenticarPorteiro(@ModelAttribute LoginDTO loginDTO, HttpSession session) {
        Porteiro porteiroLogado = porteiroService.autenticarPorteiro(loginDTO);
        session.setAttribute("usuarioLogado", porteiroLogado);
        return "redirect:/sistema";
    }

    // Página de cadastro de conta
    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("registroDTO", new RegistroDTO());
        return "acesso/cadastro";
    }

    // Salva o cadastro no banco e redireciona para o login
    @PostMapping("/cadastrar")
    public String registrarNovoPorteiro(@ModelAttribute RegistroDTO registroDTO) {
        porteiroService.registrarNovoPorteiro(registroDTO);
        return "redirect:/login";
    }

    // Página de recuperação de senha
    @GetMapping("/recuperar-senha")
    public String mostrarFormularioRecuperarSenha(Model model) {
        model.addAttribute("porteiro", new Porteiro());
        return "acesso/recuperar_senha";
    }
}
