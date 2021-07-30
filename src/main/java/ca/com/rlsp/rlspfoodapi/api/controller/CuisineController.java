package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.model.CuisineXMLWrapper;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundIntoDBException;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityIsForeignKeyException;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.repository.CuisineRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.CuisineRegistrationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cuisines", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CuisineController {

    @Autowired
    private  CuisineRepository cuisineRepository;

    @Autowired
    private CuisineRegistrationService cuisineRegistrationService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Cuisine> listAll(){
        return cuisineRegistrationService.listAll();
    }

    @ResponseStatus(HttpStatus.OK) // Codigo de Resposta do Servidor quue sera enviado para essa requisaicao
    @GetMapping("/{cuisineId}")
    public ResponseEntity<Cuisine> findBy1Id(@PathVariable("cuisineId") Long id){
        Optional<Cuisine> cuisine =  cuisineRepository.findById(id);

        //return ResponseEntity.ok(cuisine);
        if(cuisine.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(cuisine.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CuisineXMLWrapper listAllXML(){
        return new CuisineXMLWrapper(cuisineRegistrationService.listAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine save(@RequestBody Cuisine cuisine){

        return cuisineRegistrationService.save(cuisine);
    }

    @PutMapping("/{cuisineId}")
    public ResponseEntity<?> updateById(@PathVariable("cuisineId") Long id, @RequestBody Cuisine cuisine){
        try {
            Optional<Cuisine> currentCuisine = cuisineRepository.findById(id);

            if (currentCuisine.isPresent()) {
                //currentCuisine.setName(cuisine.getName());
                BeanUtils.copyProperties(cuisine, currentCuisine.get(), "id"); // Copia (novo, antigo) objeto de cuisine
                Cuisine cuisineSaved = cuisineRepository.save(currentCuisine.get());

                return ResponseEntity.status(HttpStatus.OK).body(cuisineSaved);
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundIntoDBException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{cuisineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("cuisineId") Long id) {
            cuisineRegistrationService.remove(id);
    }

    /*
    @DeleteMapping("/{cuisineId}")
    public ResponseEntity<Cuisine> remove(@PathVariable("cuisineId") Long id) {
        try {
            cuisineRegistrationService.remove(id);
            return ResponseEntity.noContent().build();

        } catch(EntityNotFoundIntoDBException e){
            return ResponseEntity.notFound().build();

        } catch (EntityIsForeignKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    */

    /*
    @DeleteMapping("/{cuisineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("cuisineId") Long id) {
        try{
            cuisineRegistrationService.remove(id);
        } catch (EntityNotFoundIntoDBException e){
            // Usando nova classe do SpringBoot 5 => customiza apenas o Status HTTP e a Mensagem
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  e.getMessage());
            //throw new ServerWebInputException(e.getMessage());
        }
    }
    */



}
