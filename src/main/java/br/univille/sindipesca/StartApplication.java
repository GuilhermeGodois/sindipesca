package br.univille.sindipesca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import br.univille.sindipesca.model.Usuario;
import br.univille.sindipesca.service.impl.MyUserDetailsService;

@Component
public class StartApplication implements ApplicationRunner{
    @Autowired
    private MyUserDetailsService service;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Usuario usuarioAdmin = service.buscaUsuario("admin");
        //System.out.println("Buscando");
        if(usuarioAdmin == null){
            //System.out.println("Nao achou");
            usuarioAdmin = new Usuario();
            usuarioAdmin.setUsuario("admin");
            usuarioAdmin.setSenha("admin");
            service.save(usuarioAdmin);
            System.out.println("Salva senha " + usuarioAdmin.getSenha());
        }
        System.out.println("Salva senha " + usuarioAdmin.getSenha());
    }

}
