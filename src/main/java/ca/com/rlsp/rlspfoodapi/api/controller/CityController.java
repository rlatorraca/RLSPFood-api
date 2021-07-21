package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.domain.exception.EntityIsForeignKeyException;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundIntoDBException;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import ca.com.rlsp.rlspfoodapi.domain.repository.CityRepository;
import ca.com.rlsp.rlspfoodapi.domain.repository.ProvinceRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.CityRegistrationService;
import ca.com.rlsp.rlspfoodapi.domain.service.ProvinceRegistrationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
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

    @GetMapping("/{cityId}}")
    public ResponseEntity<City> findById(@PathVariable("cityId") Long id) {
        Optional<City> city = cityRepository.findById(id);

        if (city.isPresent()) {
            return ResponseEntity.ok(city.get());
        }

        return ResponseEntity.notFound().build();


    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  save(@RequestBody City city) {

        try {
            city = cityRegistrationService.save(city);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(city);
        } catch (EntityNotFoundIntoDBException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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

    @DeleteMapping("/{cityId}")
    public ResponseEntity<?> remover(@PathVariable("cityId") Long id) {
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


}
