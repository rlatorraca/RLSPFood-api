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

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRegistrationService cityRegistrationService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<City> listAllJson(){
        return cityRegistrationService.listAll();
    }

    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE})
    public List<City> listAllXml(){
        return cityRegistrationService.listAll();
    }

    @GetMapping("/{cityId}}")
    public ResponseEntity<City> findById(@PathVariable("cityId") Long id) {
        City city = cityRegistrationService.findById(id);

        if (city != null) {
            return ResponseEntity.ok(city);
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
        City currentCity = cityRegistrationService.findById(id);

        if (currentCity != null) {
            BeanUtils.copyProperties(city, currentCity, "id");

            currentCity = cityRegistrationService.save(currentCity);
            return ResponseEntity.ok(currentCity);
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
