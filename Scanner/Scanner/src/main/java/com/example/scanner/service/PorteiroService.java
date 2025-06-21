package com.example.scanner.service;

import com.example.scanner.model.Porteiro;
import com.example.scanner.model.Usuario;
import com.example.scanner.repository.PorteiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    // Se precisar usar descomente
    /*
    public List<Porteiro> listarTodos() {
        return porteiroRepository.findAll();
    }

    public Optional<Porteiro> buscarPorId(Integer id) {
        return porteiroRepository.findById(id);
    } */
}
