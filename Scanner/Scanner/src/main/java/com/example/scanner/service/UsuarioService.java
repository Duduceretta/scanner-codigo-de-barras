package com.example.scanner.service;

import com.example.scanner.dto.UsuarioDTO;
import com.example.scanner.exception.UsuarioJaCadastradoException;
import com.example.scanner.model.Usuario;
import com.example.scanner.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public void registrarNovoUsuario(UsuarioDTO usuarioDTO) {
        verificarSeOUsuarioJaEstaRegistrado(usuarioDTO.getCodigoDeBarras());

        Usuario usuario = new Usuario(
                usuarioDTO.getNome(),
                usuarioDTO.getCodigoDeBarras()
        );

        usuarioRepository.save(usuario);
    }

    private void verificarSeOUsuarioJaEstaRegistrado(String codigoDeBarras) {
        if(usuarioRepository.findByCodigoDeBarras(codigoDeBarras).isPresent()) {
            throw new UsuarioJaCadastradoException("Ja existe um Usuario cadastrado com o mesmo codigo de barras no sistema");
        }
    }

    public void excluir(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> buscarPorCodigoBarra(String codigo) {
        return usuarioRepository.findByCodigoDeBarras(codigo);
    }
}
