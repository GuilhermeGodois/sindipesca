package br.univille.sindipesca.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.sindipesca.model.Usuario;
import br.univille.sindipesca.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioControllerAPI {
    @Autowired
    private UsuarioService service;
    
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        List<Usuario> lista = service.getAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") Usuario usuario){
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> save (@RequestBody Usuario usuario){
        service.save(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable("id") Usuario usuarioAntigo, 
                                                @RequestBody Usuario usuarioAtualizado){
    usuarioAntigo.setNome(usuarioAtualizado.getNome());    
    usuarioAntigo.setDataNascimento(usuarioAtualizado.getDataNascimento());
    usuarioAntigo.setSexo(usuarioAtualizado.getSexo());
    service.save(usuarioAntigo);
     return new ResponseEntity<>(usuarioAntigo, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable("id") Usuario usuario){
        service.delete(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
