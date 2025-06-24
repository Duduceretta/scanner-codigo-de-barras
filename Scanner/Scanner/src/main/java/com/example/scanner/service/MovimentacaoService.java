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

    @Transactional
    public Movimentacao registrarMovimentacao(Usuario usuario, Item item, Movimentacao.TipoMovimentacao tipo) {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setUsuario(usuario);
        movimentacao.setItem(item);
        movimentacao.setTipo(tipo);
        movimentacao.setDataHora(LocalDateTime.now());
        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> listarTodas() {
        return movimentacaoRepository.findAll();
    }

    public Optional<Movimentacao> buscarUltimaMovimentacaoDoItem(Integer itemId) {
        return movimentacaoRepository.findTopByItemIdOrderByDataHoraDesc(itemId);
    }
}
