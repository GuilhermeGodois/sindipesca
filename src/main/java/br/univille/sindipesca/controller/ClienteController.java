package br.univille.sindipesca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.sindipesca.model.Cliente;
import br.univille.sindipesca.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService service;

    @GetMapping("")
    public ModelAndView index(){
        List<Cliente> listaCliente = service.getAll();
        return new ModelAndView("cliente/index", "listaclientes",listaCliente);
    }
    @GetMapping("/novo")
    public ModelAndView novo(@ModelAttribute Cliente cliente){
        return new ModelAndView("cliente/form");
    }

} 


