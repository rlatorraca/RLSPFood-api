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

import java.util.List;

@RestController
@RequestMapping(value = "/cuisines", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CuisineController {

    //@Autowired
    //private  CuisineRepository cuisineRepository;

    @Autowired
    private CuisineRegistrationService cuisineRegistrationService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Cuisine> listAll(){
        return cuisineRegistrationService.listAll();
    }

    @ResponseStatus(HttpStatus.OK) // Codigo de Resposta do Servidor quue sera enviado para essa requisaicao
    @GetMapping("/{cuisineId}")
    public ResponseEntity<Cuisine> findBy1Id(@PathVariable("cuisineId") Long id){
        Cuisine cuisine =  cuisineRegistrationService.findById(id);

        //return ResponseEntity.ok(cuisine);
        if(cuisine != null){
            return ResponseEntity.status(HttpStatus.OK).body(cuisine);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CuisineXMLWrapper listAllXML(){
        return new CuisineXMLWrapper(cuisineRegistrationService.listAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine add(@RequestBody Cuisine cuisine){

        return cuisineRegistrationService.save(cuisine);
    }

    @PutMapping("/{cuisineId}")
    public ResponseEntity<Cuisine> updateById(@PathVariable("cuisineId") Long id, @RequestBody Cuisine cuisine){
        Cuisine currentCuisine = cuisineRegistrationService.findById(id);

        if(currentCuisine != null){
            //currentCuisine.setName(cuisine.getName());
            BeanUtils.copyProperties(cuisine, currentCuisine, "id"); // Copia (novo, antigo) objeto de cuisine
            currentCuisine = cuisineRegistrationService.save(currentCuisine);

            return ResponseEntity.status(HttpStatus.OK).body(currentCuisine);
        }

        return  ResponseEntity.notFound().build();

    }

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

}
