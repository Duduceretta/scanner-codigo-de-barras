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

@Controller
@RequestMapping("/")
public class AcessoController {

    @Autowired
    private PorteiroService porteiroService;

    @GetMapping("/")
    public String redirecionarParaLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "acesso/login";
    }

    @PostMapping("/login")
    public String autenticarPorteiro(@ModelAttribute LoginDTO loginDTO, HttpSession session) {
        Porteiro porteiroLogado = porteiroService.autenticarPorteiro(loginDTO);
        session.setAttribute("usuarioLogado", porteiroLogado);
        return "redirect:/sistema";
    }

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("registroDTO", new RegistroDTO());
        return "acesso/cadastro";
    }

    @PostMapping("/cadastrar")
    public String registrarNovoPorteiro(@ModelAttribute RegistroDTO registroDTO) {
        porteiroService.registrarNovoPorteiro(registroDTO);
        return "redirect:/login";
    }

    @GetMapping("/recuperar-senha")
    public String mostrarFormularioRecuperarSenha(Model model) {
        model.addAttribute("porteiro", new Porteiro());
        return "acesso/recuperar_senha";
    }
}
