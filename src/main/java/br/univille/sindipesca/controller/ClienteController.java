package br.univille.sindipesca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(params="form")
    public ModelAndView save(Cliente cliente){
        service.save(cliente);
        return new ModelAndView("redirect:/cliente");
    }

    @GetMapping(value="/alterar/{id}")
    public ModelAndView edit(@PathVariable("id") Cliente cliente){
        return new ModelAndView("cliente/form","cliente",cliente);
    }

    @GetMapping(value="/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Cliente cliente){
        service.delete(cliente);
        return new ModelAndView("redirect:/cliente");
    }

} 


