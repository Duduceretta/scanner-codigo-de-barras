package com.example.scanner.controller;

import com.example.scanner.model.Item;
import com.example.scanner.model.Movimentacao;
import com.example.scanner.model.Usuario;
import com.example.scanner.service.ItemService;
import com.example.scanner.service.MovimentacaoService;
import com.example.scanner.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
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
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorCodigoBarra(codigoUsuario);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado.");
        }

        Optional<Item> itemOpt = itemService.buscarPorCodigoBarra(codigoItem);
        if (itemOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item não encontrado.");
        }

        Item item = itemOpt.get();
        if (item.getStatus() != Item.StatusItem.disponivel) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item não está disponível para empréstimo.");
        }

        // Atualiza status do item para emprestado
        item.setStatus(Item.StatusItem.emprestado);
        itemService.salvar(item);

        // Registra movimentação de empréstimo
        movimentacaoService.registrarMovimentacao(usuarioOpt.get(), item, Movimentacao.TipoMovimentacao.EMPRESTIMO);

        return ResponseEntity.ok("Movimentação registrada com sucesso.");
    }

    @GetMapping("/movimentos")
    public String listarMovimentacoes(Model model) {
        List<Movimentacao> movimentos = movimentacaoService.listarTodas();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        movimentos.forEach(mov -> {
            if (mov.getDataHora() != null) {
                mov.setDataFormatada(mov.getDataHora().format(formatter));
            }
        });

        model.addAttribute("movimentos", movimentos);
        return "scanner/movimento";
    }

    @PostMapping("/devolver")
    @ResponseBody
    public ResponseEntity<String> devolverItem(@RequestParam("codigoItem") String codigoItem) {
        Optional<Item> itemOpt = itemService.buscarPorCodigoBarra(codigoItem);
        if (itemOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item não encontrado.");
        }

        Item item = itemOpt.get();
        if (item.getStatus() != Item.StatusItem.emprestado) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item não está emprestado.");
        }

        // Atualiza status para disponível
        item.setStatus(Item.StatusItem.disponivel);
        itemService.salvar(item);

        Optional<Movimentacao> ultimaMovOpt = movimentacaoService.buscarUltimaMovimentacaoDoItem(item.getId());
        if (ultimaMovOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar última movimentação.");
        }

        Usuario usuario = ultimaMovOpt.get().getUsuario();
        movimentacaoService.registrarMovimentacao(usuario, item, Movimentacao.TipoMovimentacao.DEVOLUCAO);

        return ResponseEntity.ok("Item devolvido com sucesso.");
    }
}
