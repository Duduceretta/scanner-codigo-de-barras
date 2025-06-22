package com.example.scanner.service;

import com.example.scanner.model.Item;
import com.example.scanner.model.Movimentacao;
import com.example.scanner.model.Usuario;
import com.example.scanner.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public Movimentacao registrarMovimentacao(Usuario usuario, Item item, Movimentacao.TipoMovimentacao tipo) {
        Movimentacao mov = new Movimentacao();
        mov.setUsuario(usuario);
        mov.setItem(item);
        mov.setTipo(tipo);
        mov.setDataHora(LocalDateTime.now());
        return movimentacaoRepository.save(mov);
    }

    public List<Movimentacao> listarTodas() {
        return movimentacaoRepository.findAll();
    }
}
