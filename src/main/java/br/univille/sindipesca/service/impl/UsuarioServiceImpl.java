package br.univille.sindipesca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.sindipesca.model.Usuario;
import br.univille.sindipesca.repository.UsuarioRepository;
import br.univille.sindipesca.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void save(Usuario usuario) {
        repository.save(usuario);

    }

    @Override
    public void delete(Usuario usuario) {
        repository.delete(usuario);

    }

    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }
    
}