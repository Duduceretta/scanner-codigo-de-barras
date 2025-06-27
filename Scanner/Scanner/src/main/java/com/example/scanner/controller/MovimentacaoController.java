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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class MovimentacaoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping("/movimentos/confirmar")
    @ResponseBody
    @Transactional
    public synchronized ResponseEntity<String> confirmarMovimentacao(
            @RequestParam String codigoUsuario,
            @RequestParam String codigoItem) {

        try {
            // Busca usuário
            Optional<Usuario> usuarioOpt = usuarioService.buscarPorCodigoBarra(codigoUsuario);
            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Usuário não encontrado.");
            }

            // Busca item
            Optional<Item> itemOpt = itemService.buscarPorCodigoBarra(codigoItem);
            if (itemOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Item não encontrado.");
            }

            Item item = itemOpt.get();

            // CRÍTICO: Recarrega o item do banco para ter o status mais atual
            item = itemService.buscarPorId(item.getId()).orElse(item);

            // Verifica se já está emprestado pelo status do item
            if (item.getStatus() == Item.StatusItem.emprestado) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Item já está emprestado.");
            }

            // Dupla verificação: busca última movimentação
            Optional<Movimentacao> ultimaMovOpt = movimentacaoService.buscarUltimaMovimentacaoDoItem(item.getId());
            if (ultimaMovOpt.isPresent()) {
                Movimentacao ultimaMov = ultimaMovOpt.get();
                if (ultimaMov.getTipo() == Movimentacao.TipoMovimentacao.EMPRESTIMO) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Item já está emprestado.");
                }
            }

            // Atualiza status ANTES de registrar movimentação
            item.setStatus(Item.StatusItem.emprestado);
            itemService.salvar(item);

            // Registra a movimentação
            movimentacaoService.registrarMovimentacao(
                    usuarioOpt.get(),
                    item,
                    Movimentacao.TipoMovimentacao.EMPRESTIMO
            );

            return ResponseEntity.ok("Movimentação registrada com sucesso.");

        } catch (Exception e) {
            // Log do erro
            System.err.println("Erro ao confirmar movimentação: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor.");
        }
    }

    @GetMapping("/movimentos")
    public String listarMovimentacoes(Model model) {
        List<Movimentacao> movimentos = movimentacaoService.listarTodasOrdenadas();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        movimentos.forEach(mov -> {
            if (mov.getDataHora() != null) {
                mov.setDataFormatada(mov.getDataHora().format(formatter));
            }
        });

        model.addAttribute("movimentos", movimentos);
        return "scanner/log_movimentacoes";
    }

    @GetMapping("/movimentos/listar-emprestados")
    public String listarEmprestados(Model model) {
        List<Movimentacao> movimentos = movimentacaoService.listarEmprestimosAtivos();
        model.addAttribute("movimentos", movimentos);
        return "scanner/devolucao_itens";
    }

    @PostMapping("/movimentos/devolver/{id}")
    @Transactional
    public String devolverItem(@PathVariable Integer id) {
        movimentacaoService.registrarDevolucao(id);
        return "redirect:/movimentos/listar-emprestados";
    }
}
