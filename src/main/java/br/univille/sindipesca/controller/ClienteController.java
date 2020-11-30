package br.univille.sindipesca.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.univille.sindipesca.model.Cliente;
import br.univille.sindipesca.model.ItensDocumento;
import br.univille.sindipesca.service.ClienteService;

@Controller
@RequestMapping({"/cliente"})
public class ClienteController {


    @Autowired
    private ClienteService service;

    @GetMapping({""})
    public ModelAndView index(){
        List<Cliente> lista = service.getAll();
        return new ModelAndView("cliente/index","listaclientes",lista);
    }   

    @GetMapping("/novo")
    public ModelAndView novo(@ModelAttribute Cliente cliente){
        return new ModelAndView("cliente/form");
    }
    @GetMapping(value="/alterar/{id}")
    public ModelAndView edit(@PathVariable("id") Cliente cliente){
        return new ModelAndView("cliente/form","cliente",cliente);
    }
    @GetMapping(value="/detalhar/{id}")
    public ModelAndView detail(@PathVariable("id") Cliente cliente){
        HashMap<String,Object> dados = new HashMap();
        dados.put("cliente",cliente);
        dados.put("detalhar",true);
        return new ModelAndView("cliente/form",dados);
    }



    @GetMapping(value="/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Cliente cliente){
        service.delete(cliente);
        return new ModelAndView("redirect:/cliente");
    }

    @GetMapping(value="/delete/{id}/deleteitem/{iditem}")
    public ModelAndView delete(@PathVariable("id") Cliente cliente, @PathVariable("iditem") ItensDocumento item){
        service.deleteItem(cliente,item);
        return new ModelAndView("redirect:/cliente");
    }

    @PostMapping(params={"form"})
    public ModelAndView save(Cliente cliente, @RequestParam("files") MultipartFile[] files){

        service.uploadFiles(cliente, files);

        return new ModelAndView("redirect:/cliente");
    }


    @RequestMapping(path = "/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@PathVariable("id") ItensDocumento item) throws IOException {

        InputStreamResource resource = service.downloadFile(item);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + item.getArquivo())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


}


