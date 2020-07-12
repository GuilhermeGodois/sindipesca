package br.univille.sindipesca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.univille.sindipesca.model.Usuario;

@Service
public interface UsuarioService {
    void save(Usuario usuario);
    void delete(Usuario usuario);
    List<Usuario> getAll();
}