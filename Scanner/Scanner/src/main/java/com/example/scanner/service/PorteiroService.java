package com.example.scanner.service;

import com.example.scanner.model.Porteiro;
import com.example.scanner.repository.PorteiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PorteiroService {
    @Autowired
    private PorteiroRepository porteiroRepository;

    public void salvar(Porteiro porteiro) {
        porteiroRepository.save(porteiro);
    }

    public Optional<Porteiro> buscarPorEmail(String email) {
        return porteiroRepository.findByEmail(email);
    }
}
