package com.example.scanner.repository;

import com.example.scanner.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Optional<Item> findByCodigoDeBarras(String codigoBarra);
}
