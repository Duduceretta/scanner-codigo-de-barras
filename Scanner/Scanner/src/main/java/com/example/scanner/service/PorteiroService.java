package com.example.scanner.service;

import com.example.scanner.dto.LoginDTO;
import com.example.scanner.exception.AutenticacaoException;
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

    public Porteiro autenticar(LoginDTO loginDTO) {
        Porteiro porteiro = porteiroRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new AutenticacaoException("Email não encontrado"));

        if (!porteiro.getSenha().equals(loginDTO.getSenha())) {
            throw new AutenticacaoException("Senha Incorreta.")
        }

        return porteiro;
    }
}
