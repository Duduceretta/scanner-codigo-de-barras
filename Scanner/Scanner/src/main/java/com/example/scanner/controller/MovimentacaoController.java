package com.example.scanner.controller;

import com.example.scanner.model.Item;
import com.example.scanner.model.Movimentacao;
import com.example.scanner.model.Usuario;
import com.example.scanner.service.ItemService;
import com.example.scanner.service.MovimentacaoService;
import com.example.scanner.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/sistema")
public class MovimentacaoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping("/confirmar")
    @ResponseBody
    public ResponseEntity<String> confirmarMovimentacao(@RequestParam String codigoUsuario, @RequestParam String codigoItem) {

        Optional<Usuario> usuario = usuarioService.buscarPorCodigoBarra(codigoUsuario);
        Optional<Item> item = itemService.buscarPorCodigoBarra(codigoItem);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado.");
        }

        if (item.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item não encontrado.");
        }

        if (item.get().getStatus() != Item.StatusItem.disponivel) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item não está disponível.");
        }

        // Atualiza status do item para "emprestado"
        Item itemAtualizado = item.get();
        itemAtualizado.setStatus(Item.StatusItem.emprestado);
        itemService.salvar(itemAtualizado);

        // Registra a movimentação como empréstimo
        movimentacaoService.registrarMovimentacao(usuario.get(), itemAtualizado, Movimentacao.TipoMovimentacao.EMPRESTIMO);

        return ResponseEntity.ok("Movimentação registrada com sucesso.");
    }

    @GetMapping("/movimentos")
    public String listarMovimentacoes(org.springframework.ui.Model model) {
        model.addAttribute("movimentos", movimentacaoService.listarTodas());
        return "scanner/movimento"; // o nome do seu HTML
    }
}
