package com.example.scanner.repository;

import com.example.scanner.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {
    Optional<Movimentacao> findTopByItemIdOrderByDataHoraDesc(Integer itemId);
}
