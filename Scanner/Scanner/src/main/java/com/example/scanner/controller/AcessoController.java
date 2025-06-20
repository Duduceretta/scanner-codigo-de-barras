package com.example.scanner.controller;

import com.example.scanner.model.Porteiro;
import com.example.scanner.service.PorteiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AcessoController {

    @Autowired
    private PorteiroService porteiroService;

    //Redireciona para a pagina de login
    @GetMapping("/")
    public String redirecionarParaLogin() {
        return "redirect:/login";
    }

    //Pagina principal - login do porteiro no sistema
    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("porteiro", new Porteiro());
        return "acesso/login";
    }

    //Pagina para criar uma conta
    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("porteiro", new Porteiro());
        return "acesso/cadastro";
    }

    //Vai para a pagina de recuperacao de senha
    @GetMapping("/recuperar-senha")
    public String mostrarFormularioRecuperarSenha(Model model) {
        model.addAttribute("porteiro", new Porteiro());
        return "acesso/recuperar_senha";
    }



}
