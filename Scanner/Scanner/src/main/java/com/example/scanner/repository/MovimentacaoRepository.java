package com.example.scanner.repository;

import com.example.scanner.model.Item;
import com.example.scanner.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {
    Optional<Movimentacao> findTopByItemIdOrderByDataHoraDesc(Integer itemId);
    List<Movimentacao> findByTipoAndItem_Status(Movimentacao.TipoMovimentacao tipo, Item.StatusItem status);
    List<Movimentacao> findAllByOrderByUsuario_NomeAscItem_NomeAscDataHoraDesc();
    @Query("SELECT m FROM Movimentacao m WHERE m.tipo = 'EMPRESTIMO' " +
            "AND NOT EXISTS (SELECT d FROM Movimentacao d WHERE d.item.id = m.item.id " +
            "AND d.tipo = 'DEVOLUCAO' AND d.dataHora > m.dataHora)")
    List<Movimentacao> findEmprestimosAtivos();
}
