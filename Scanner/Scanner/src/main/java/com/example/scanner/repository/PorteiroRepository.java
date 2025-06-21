package com.example.scanner.repository;

import com.example.scanner.model.Porteiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PorteiroRepository extends JpaRepository<Porteiro, Integer> {
    Optional<Porteiro> findByEmail(String email);
}
