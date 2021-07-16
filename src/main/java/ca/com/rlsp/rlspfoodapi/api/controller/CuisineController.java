package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.model.CuisineXMLWrapper;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cuisines", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CuisineController {

    @Autowired
    public  CuisineRepository cuisineRepository;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Cuisine> listAll(){
        return cuisineRepository.listAll();
    }

    @ResponseStatus(HttpStatus.OK) // Codigo de Resposta do Servidor quue sera enviado para essa requisaicao
    @GetMapping("/{cuisineId}")
    public Cuisine findBy1Id(@PathVariable("cuisineId") Long id){
        return cuisineRepository.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CuisineXMLWrapper listAllXML(){
        return new CuisineXMLWrapper(cuisineRepository.listAll());
    }
}
