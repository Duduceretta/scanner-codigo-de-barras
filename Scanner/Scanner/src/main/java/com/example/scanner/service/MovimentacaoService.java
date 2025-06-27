package com.example.scanner.service;

import com.example.scanner.model.Item;
import com.example.scanner.model.Movimentacao;
import com.example.scanner.model.Usuario;
import com.example.scanner.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ItemService itemService;

    @Transactional
    public Movimentacao registrarMovimentacao(Usuario usuario, Item item, Movimentacao.TipoMovimentacao tipo) {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setUsuario(usuario);
        movimentacao.setItem(item);
        movimentacao.setTipo(tipo);
        movimentacao.setDataHora(LocalDateTime.now());
        return movimentacaoRepository.save(movimentacao);
    }

    public Movimentacao save(Movimentacao mov) {
        return movimentacaoRepository.save(mov);
    }

    // Método principal para devolução
    @Transactional
    public void registrarDevolucao(Integer movimentacaoId) {
        Optional<Movimentacao> movOptional = movimentacaoRepository.findById(movimentacaoId);

        if (movOptional.isEmpty()) {
            throw new RuntimeException("Movimentação não encontrada.");
        }

        Movimentacao emprestimo = movOptional.get();
        Item item = emprestimo.getItem();

        if (item.getStatus() != Item.StatusItem.emprestado) {
            throw new RuntimeException("Item não está emprestado - não pode ser devolvido.");
        }

        // Atualiza status para disponível
        item.setStatus(Item.StatusItem.disponivel);
        itemService.salvar(item);

        // Cria nova movimentação de devolução
        Movimentacao devolucao = new Movimentacao();
        devolucao.setItem(item);
        devolucao.setUsuario(emprestimo.getUsuario());
        devolucao.setTipo(Movimentacao.TipoMovimentacao.DEVOLUCAO);
        devolucao.setDataHora(LocalDateTime.now());

        movimentacaoRepository.save(devolucao);
    }

    public Optional<Movimentacao> buscarUltimaMovimentacaoDoItem(Integer itemId) {
        return movimentacaoRepository.findTopByItemIdOrderByDataHoraDesc(itemId);
    }

    public List<Movimentacao> listarTodas() {
        return movimentacaoRepository.findAll();
    }

    public List<Movimentacao> listarTodasOrdenadas() {
        return movimentacaoRepository.findAllByOrderByUsuario_NomeAscItem_NomeAscDataHoraDesc();
    }

    public List<Movimentacao> listarEmprestimosAtivos() {
        return movimentacaoRepository.findEmprestimosAtivos();
    }

}
