package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.exceptionhandler.ApiHandleError;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.exception.ProvinceNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.repository.CityRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.CityRegistrationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    private CityRegistrationService cityRegistrationService;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<City> listAllJson(){
        return cityRepository.findAll();
    }

    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE})
    public List<City> listAllXml(){
        return cityRepository.findAll();
    }


    @GetMapping("/{cityId}")
    public City findById(@PathVariable Long cityId) {
        System.out.println("@GetMapping(\"/{cityId}}\")");
        return cityRegistrationService.findOrFail(cityId);
    }

    /*
    @GetMapping("/{cityId}}")
    public ResponseEntity<City> findById(@PathVariable("cityId") Long id) {
        Optional<City> city = cityRepository.findById(id);

        if (city.isPresent()) {
            return ResponseEntity.ok(city.get());
        }

        return ResponseEntity.notFound().build();
    }
    */


    /*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(@RequestBody City city) {

        try {
            city = cityRegistrationService.save(city);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(city);
        } catch (EntityNotFoundIntoDBException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City save(@RequestBody City city) {
        try{
            return cityRegistrationService.save(city);
        } catch (EntityNotFoundException e ){
            throw new GenericBusinessException(e.getMessage(), e);
        }
    }

    /*
    @PutMapping("/{cityId}")
    public ResponseEntity<City> updateById(@PathVariable("cityId") Long id,
                                               @RequestBody City city) {
        Optional<City> currentCity = cityRegistrationService.findById(id);

        if (currentCity.isPresent()) {
            BeanUtils.copyProperties(city, currentCity.get(), "id");

            City newCity = cityRegistrationService.save(currentCity.get());
            return ResponseEntity.ok(newCity);
        }

        return ResponseEntity.notFound().build();
    }
    */

    @PutMapping("/{cityId}")
    public City updateById(@PathVariable("cityId") Long id,
                                           @RequestBody City city) {
        try{
            City currentCity = cityRegistrationService.findOrFail(id);
            BeanUtils.copyProperties(city, currentCity, "id");
            return cityRegistrationService.save(currentCity);
        } catch (ProvinceNotFoundException e ){
            throw new GenericBusinessException(e.getMessage(), e.getCause());
        }
    }

    /*
    @DeleteMapping("/{cityId}")
    public ResponseEntity<?> delete(@PathVariable("cityId") Long id) {
        try {
            cityRegistrationService.remove(id);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundIntoDBException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityIsForeignKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
    */

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("cityId") Long id) {
        cityRegistrationService.remove(id);
    }

    /*
        Metodo que trata a Excecao Generica e Cria uma mensagem de Errro Customizada
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e){
        ApiHandleError handler = ApiHandleError.builder()
                .dateTime(LocalDateTime.now())
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(handler);
    }

    @ExceptionHandler(GenericBusinessException.class)
    public ResponseEntity<?> handleGenericBusinessException(GenericBusinessException e){
        ApiHandleError handler = ApiHandleError.builder()
                .dateTime(LocalDateTime.now())
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(handler);
    }

}
