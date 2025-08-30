package com.example.scanner.service;

import com.example.scanner.dto.LoginDTO;
import com.example.scanner.dto.RegistroDTO;
import com.example.scanner.exception.AutenticacaoException;
import com.example.scanner.exception.EmailJaCadastradoException;
import com.example.scanner.exception.SenhasNaoCoincidemExcepetion;
import com.example.scanner.model.Porteiro;
import com.example.scanner.repository.PorteiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PorteiroService {
    @Autowired
    private PorteiroRepository porteiroRepository;

    public void registrarNovoPorteiro(RegistroDTO registroDTO) {
        validarEmailNaoExistente(registroDTO.getEmail());
        verificarSeAsSenhasSaoIguais(registroDTO.getSenha(), registroDTO.getSenhaConfirmacao());

        System.out.println(registroDTO.getNome());

        Porteiro porteiro = new Porteiro(
                registroDTO.getNome(),
                registroDTO.getEmail(),
                registroDTO.getSenha()
        );

        porteiroRepository.save(porteiro);
    }

    private void validarEmailNaoExistente(String email) {
        if(porteiroRepository.findByEmail(email).isPresent()){
            throw new EmailJaCadastradoException("Ja existe uma conta com este email");
        }
    }

    private void verificarSeAsSenhasSaoIguais(String senha, String senhaConfirmacao) {
        if(!senha.equals(senhaConfirmacao)){
            throw new SenhasNaoCoincidemExcepetion("As senhas digitadas nao coincidem");
        }
    }

    public Porteiro autenticarPorteiro(LoginDTO loginDTO) {
        Porteiro porteiro = buscarPorteiroPorEmail(loginDTO.getEmail());
        validarSenha(loginDTO.getSenha(), porteiro.getSenha());
        return porteiro;
    }

    private Porteiro buscarPorteiroPorEmail(String email) {
        return porteiroRepository.findByEmail(email).orElseThrow(() -> new AutenticacaoException("Email ou senha inválidos"));
    }

    private void validarSenha(String senhaFornecida, String senhaCadastrada) {
        if (!senhaFornecida.equals(senhaCadastrada)) {
            throw new AutenticacaoException("Email ou senha inválidos");
        }
    }

}
