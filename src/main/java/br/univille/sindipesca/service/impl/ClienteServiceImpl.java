package br.univille.sindipesca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.sindipesca.model.Cliente;
import br.univille.sindipesca.repository.ClienteRepository;
import br.univille.sindipesca.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public void save(Cliente cliente) {
        repository.save(cliente);

    }

    @Override
    public void delete(Cliente cliente) {
        repository.delete(cliente);

    }

    @Override
    public List<Cliente> getAll() {
        
        return repository.findAll();
    }
    
}
