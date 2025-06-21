package com.example.scanner.controller;

import com.example.scanner.model.Porteiro;
import com.example.scanner.service.PorteiroService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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
    public String processarLogin(@RequestParam String email,
                                 @RequestParam String password,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {

        Optional<Porteiro> porteiroOpt = porteiroService.buscarPorEmail(email);

        if (porteiroOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "E-mail não encontrado");
            return "redirect:/login";
        }

        Porteiro porteiro = porteiroOpt.get();

        if (!porteiro.getSenha().equals(password)) {
            redirectAttributes.addFlashAttribute("erro", "Senha incorreta");
            return "redirect:/login";
        }

        // Login bem-sucedido
        session.setAttribute("usuarioLogado", porteiro);
        return "redirect:/sistema";
    }

    // Página de cadastro de conta
    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("porteiro", new Porteiro());
        return "acesso/cadastro";
    }

    // Salva o cadastro no banco e redireciona para o login
    @PostMapping("/cadastrar")
    public String processarCadastro(@ModelAttribute Porteiro porteiro,
                                 RedirectAttributes redirectAttributes) {

        // Verifica se o e-mail já está cadastrado
        Optional<Porteiro> existente = porteiroService.buscarPorEmail(porteiro.getEmail());
        if (existente.isPresent()) {
            redirectAttributes.addFlashAttribute("erro", "Já existe uma conta com este e-mail.");
            return "redirect:/cadastro";
        }

        // Cadastrado bem-sucedido
        porteiroService.salvar(porteiro);
        return "redirect:/login";
    }

    // Página de recuperação de senha
    @GetMapping("/recuperar-senha")
    public String mostrarFormularioRecuperarSenha(Model model) {
        model.addAttribute("porteiro", new Porteiro());
        return "acesso/recuperar_senha";
    }
}
