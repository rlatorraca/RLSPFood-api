package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.CityModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.CityInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.exception.ProvinceNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.repository.CityRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.CityRegistrationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cities")
@RestController
@RequestMapping(value = "/cities")
public class CityController {

    private CityRegistrationService cityRegistrationService;
    private CityRepository cityRepository;
    private CityModelAssembler cityModelAssembler;
    private CityInputDisassembler cityInputDisassembler;

    public CityController(CityRegistrationService cityRegistrationService, 
                          CityRepository cityRepository, 
                          CityModelAssembler cityModelAssembler,                          
                          CityInputDisassembler cityInputDisassembler) {
        
        this.cityRegistrationService = cityRegistrationService;
        this.cityRepository = cityRepository;
        this.cityModelAssembler = cityModelAssembler;
        this.cityInputDisassembler = cityInputDisassembler;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    //public List<City> listAllJson(){
    public List<CityOutputDto> listAllJson(){
        List<City> allCities = cityRepository.findAll();


        return cityModelAssembler.fromControllerToOutputList(allCities);
        //return cityRepository.findAll();
    }

    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE})
    public List<City> listAllXml(){
        return cityRepository.findAll();
    }


    @GetMapping("/{cityId}")
    //public City findById(@PathVariable Long cityId) {
    public CityOutputDto findById(@PathVariable Long cityId) {
        City cidade = cityRegistrationService.findOrFail(cityId);


      //  return cityRegistrationService.findOrFail(cityId);

        return cityModelAssembler.fromControllerToOutput(cidade);
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
    //public City save(@RequestBody @Valid City city) {
    public CityOutputDto save(@RequestBody @Valid CityInputDto cityInputDTO) {
        try{
            City city = cityInputDisassembler.fromInputToController(cityInputDTO);

            city = cityRegistrationService.save(city);

            return cityModelAssembler.fromControllerToOutput(city);
            //return cityRegistrationService.save(city);
        } catch (EntityNotFoundException e ){
            throw new GenericBusinessException(e.getReason(), e);
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
    //public City updateById(@PathVariable("cityId") Long id, @RequestBody @Valid City city) {
    public CityOutputDto updateById(@PathVariable("cityId") Long id, @RequestBody @Valid CityInputDto cityInputDTO) {
        try{
            City currentCity = cityRegistrationService.findOrFail(id);

            cityInputDTO.setId(id);
            cityInputDisassembler.fromDTOtoCity(cityInputDTO, currentCity);

            currentCity = cityRegistrationService.save(currentCity);

            return cityModelAssembler.fromControllerToOutput(currentCity);
            //BeanUtils.copyProperties(city, currentCity, "id");
            //return cityRegistrationService.save(currentCity);
        } catch (ProvinceNotFoundException e ){
            throw new GenericBusinessException(e.getReason(), e);
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



}
