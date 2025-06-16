package com.example.scanner.service;

import com.example.scanner.model.Usuario;
import com.example.scanner.repository.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public List<Usuario> listarTodos() {
        return usuarioDAO.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioDAO.findById(id);
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioDAO.save(usuario);
    }

    public void excluir(Integer id) {
        usuarioDAO.deleteById(id);
    }
}
