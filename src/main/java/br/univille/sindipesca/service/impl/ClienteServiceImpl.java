package br.univille.sindipesca.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.univille.sindipesca.model.Cliente;
import br.univille.sindipesca.model.ItensDocumento;
import br.univille.sindipesca.repository.ClienteRepository;
import br.univille.sindipesca.repository.ItensDocumentoRepository;
import br.univille.sindipesca.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ItensDocumentoRepository itensDocumentorepository;
    @Value("${sindipesca.file-upload-dir}")
    private String UPLOAD_DIR;
    
    @Override
    public void save(Cliente cliente) {
        /*chamar metodo que pega usuario logado*/
        repository.save(cliente);

    }

    @Override
    public void delete(Cliente cliente) {
        repository.delete(cliente);
        for (ItensDocumento item : cliente.getItens()){
            this.deleteItem(item);
        }
        cliente.getItens().clear();
        repository.delete(cliente);
    }

    @Override
    public List<Cliente> getAll() {
        return repository.findAll();
    }
    private void createFileFolder(){
        File file = new File(UPLOAD_DIR);
        if(!file.exists()){
            file.mkdir();
        }
    }

    @Override
    public void uploadFiles(Cliente cliente, MultipartFile[] files) {
        createFileFolder();

        Optional<Cliente> oldDocumento = repository.findById(cliente.getId());
        if(oldDocumento.isPresent()){
            cliente = oldDocumento.get();
        }
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());

                try {
                    Path path = Paths.get(UPLOAD_DIR + fileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    ItensDocumento item = new ItensDocumento();
                    item.setArquivo(fileName);
                    item.setCaminho(path.toString());
                    cliente.getItens().add(item);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        repository.save(cliente);

    }
    


    @Override
    public InputStreamResource downloadFile(ItensDocumento item) throws FileNotFoundException {
        createFileFolder();
        File file = new File(item.getCaminho());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return resource;
    }


    public void deleteItem(ItensDocumento item) {
        try{
            File file = new File(item.getCaminho());
            boolean sucess = file.delete();
            if (sucess){
                itensDocumentorepository.delete(item);
            }
        }catch(Exception e){

        }
    }
    @Override
    public void deleteItem(Cliente cliente, ItensDocumento item) {
        try{
            deleteItem(item);
            cliente.getItens().remove(item);
            repository.save(cliente);
        }catch(Exception e){

        }
    }

    @Override
    public List<Cliente> findByNomeContains(String nome) {
        // TODO Auto-generated method stub
        return repository.findByNomeContainingIgnoreCase(nome);
    }

}
