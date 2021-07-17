package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.model.CuisineXMLWrapper;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.repository.CuisineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Cuisine> findBy1Id(@PathVariable("cuisineId") Long id){
        Cuisine cuisine =  cuisineRepository.findById(id);

        //return ResponseEntity.ok(cuisine);
        if(cuisine != null){
            return ResponseEntity.status(HttpStatus.OK).body(cuisine);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CuisineXMLWrapper listAllXML(){
        return new CuisineXMLWrapper(cuisineRepository.listAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine add(@RequestBody Cuisine cuisine){
        return cuisineRepository.save(cuisine);
    }

    @PutMapping("/{cuisineId}")
    public ResponseEntity<Cuisine> updateById(@PathVariable("cuisineId") Long id, @RequestBody Cuisine cuisine){
        Cuisine currentCuisine = cuisineRepository.findById(id);

        if(currentCuisine != null){
            //currentCuisine.setName(cuisine.getName());
            BeanUtils.copyProperties(cuisine, currentCuisine, "id"); // Copia (novo, antigo) objeto de cuisine
            currentCuisine = cuisineRepository.save(currentCuisine);

            return ResponseEntity.status(HttpStatus.OK).body(currentCuisine);
        }

        return  ResponseEntity.notFound().build();

    }

}
