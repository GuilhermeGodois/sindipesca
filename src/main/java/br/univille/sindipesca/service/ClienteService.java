package br.univille.sindipesca.service;

import java.util.List;
import java.util.Map;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import br.univille.sindipesca.model.Cliente;
import br.univille.sindipesca.model.ItensDocumento;
import br.univille.sindipesca.repository.ClienteRepository;
@Service
public interface ClienteService {
    void save(Cliente cliente);
    void delete(Cliente cliente);
    List<Cliente> getAll();
    void uploadFiles(Cliente cliente, MultipartFile[] files);
    InputStreamResource downloadFile(ItensDocumento item) throws FileNotFoundException;
    void deleteItem(Cliente cliente, ItensDocumento item);
    void deleteItem(ItensDocumento item);
    List<Cliente> findByNomeContains(String nome);
}
