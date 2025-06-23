    package com.example.scanner.controller;

    import com.example.scanner.model.Usuario;
    import com.example.scanner.service.UsuarioService;

    import jakarta.validation.Valid;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;

    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.*;

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

        /*
        @GetMapping
        public String listar(Model model) {
            model.addAttribute("usuarios", usuarioService.listarTodos());
            return "usuarios/lista";
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
        } */
    }
