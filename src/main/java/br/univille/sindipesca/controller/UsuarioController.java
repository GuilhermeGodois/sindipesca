package br.univille.sindipesca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.sindipesca.model.Usuario;
import br.univille.sindipesca.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
   
    @Autowired
    private UsuarioService service;


    @GetMapping("")
    public ModelAndView index(){
        List<Usuario> listaUsuarios = service.getAll();
        return new ModelAndView("usuario/index","listausuarios",listaUsuarios);
    }
    @GetMapping("/novo")
    public ModelAndView novo(){
        return new ModelAndView("usuario/form");
    }
}