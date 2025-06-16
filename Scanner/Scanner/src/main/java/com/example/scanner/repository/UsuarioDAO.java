package com.example.scanner.repository;

import com.example.scanner.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
    // Aqui pode criar métodos customizados se precisar
}
