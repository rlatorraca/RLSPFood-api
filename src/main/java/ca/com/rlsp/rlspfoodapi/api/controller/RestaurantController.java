package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.RestaurantModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.RestaurantInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CuisineInputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProvinceOutputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.RestaurantInputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.AddressOutputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CuisineOutputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.RestaurantOutputDTO;
import ca.com.rlsp.rlspfoodapi.core.validation.ValidationPatchException;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.repository.RestaurantRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.RestaurantRegistrationService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/restaurants",  produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestaurantController {

    @Autowired
    private RestaurantRegistrationService restaurantRegistrationService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Para fazer a validacao dentro no PATCH (on validateMerge())
    @Autowired
    private SmartValidator smartValidator;

    // Monta um DTO para os Model (de Entidade) para Restaurante
    @Autowired
    private RestaurantModelAssembler restaurantModelAssembler;

    // Desmont um DTO para oumModel (de Entidade) para Restaurante
    @Autowired
    private RestaurantInputDisassembler restaurantInputDisassembler;

    @GetMapping
    public List<RestaurantOutputDTO> listAll() {
        return restaurantModelAssembler.fromControllerToOutputList(restaurantRepository.newlistAll());
    }

    /*
    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> findBy1Id(@PathVariable("restaurantId") Long id){
        Optional<Restaurant> restaurant =  restaurantRegistrationService.findById(id);

        if(restaurant.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(restaurant.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
    */
    @GetMapping("/{restaurantId}")
    public RestaurantOutputDTO findBy1Id(@PathVariable("restaurantId") Long id){
        Restaurant restaurant = restaurantRegistrationService.findOrFail(id);

        RestaurantOutputDTO restaurantDTO = restaurantModelAssembler.fromControllerToOutput(restaurant);

        //return restaurantRegistrationService.findOrFail(id);
        return restaurantDTO;
    }



    /*
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantRegistrationService.save(restaurant);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurant);
        } catch (EntityNotFoundIntoDBException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //public Restaurant save(@RequestBody @Validated(GroupsBeanValidation.RestaurantValidation.class) Restaurant restaurant) {
    public RestaurantOutputDTO save(@RequestBody @Valid RestaurantInputDTO restaurantInputDTO) {
       try {
           Restaurant restaurant = restaurantInputDisassembler.fromInputToController(restaurantInputDTO); // Converte da representacao de INPUT par ao MODEL
           return restaurantModelAssembler.fromControllerToOutputPost(restaurantRegistrationService.save(restaurant));
       } catch (EntityNotFoundException e ){
           throw new GenericBusinessException(e.getReason());
       }
    }


    /*
    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> updateById(@PathVariable("restauranteId") Long id, @RequestBody Restaurant restaurant){

        try{
            Optional<Restaurant> currentRestaurant = restaurantRegistrationService.findById(id);

            if(currentRestaurant.isPresent()){
                BeanUtils.copyProperties(restaurant, currentRestaurant.get(),"id", "paymentTypeList", "address", "createdDate", "products");
                Restaurant newRestaurant = restaurantRegistrationService.save(currentRestaurant.get());

                return ResponseEntity.ok(newRestaurant);
            }

            return ResponseEntity.notFound().build();
        }catch (EntityNotFoundIntoDBException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    */
    @PutMapping("/{restauranteId}")
    public RestaurantOutputDTO updateById(@PathVariable("restauranteId") Long id, @RequestBody @Valid RestaurantInputDTO restaurantInputDTO) {
        Restaurant currentRestaurant = restaurantRegistrationService.findOrFail(id);
        Restaurant restaurant = restaurantInputDisassembler.fromInputToController(restaurantInputDTO);

        BeanUtils.copyProperties(restaurant, currentRestaurant,"id", "paymentTypeList", "address", "createdDate", "products");
        try {
            return restaurantModelAssembler.fromControllerToOutput(restaurantRegistrationService.save(currentRestaurant));
        } catch (EntityNotFoundException e ){
            throw new GenericBusinessException(e.getReason());
        }
    }

    /*
    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> updateByIdPatch(@PathVariable("restauranteId") Long id,
                                        @RequestBody Map<String, Object> restaurantFields){
        try{
            Optional<Restaurant> currentRestaurant = restaurantRegistrationService.findById(id);

            if(currentRestaurant.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Restaurant newRestaurant = mergeDataToPatch(restaurantFields, currentRestaurant.get());

            return updateById(id, newRestaurant);
        } catch (EntityNotFoundIntoDBException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }
    */
    @PatchMapping("/{restauranteId}")
    public RestaurantOutputDTO updateByIdPatch(@PathVariable("restauranteId") Long id,
                                               @RequestBody Map<String, Object> restaurantFields,
                                               HttpServletRequest request){
        Restaurant currentRestaurant = restaurantRegistrationService.findOrFail(id);
        RestaurantInputDTO currentRestaurantDTO = restaurantModelAssembler.fromControllerToInput(currentRestaurant);

        mergeDataToPatch(restaurantFields, currentRestaurantDTO, request);
        validateMerge(currentRestaurant, "restaurant");
        
        return updateById(id, currentRestaurantDTO);
    }

    // Faz a validacao de  modo programatico do PATCH
    private void validateMerge(Restaurant currentRestaurant, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(currentRestaurant, objectName );
        smartValidator.validate(currentRestaurant, bindingResult);

        // Verfica se tem ERRORS
        if(bindingResult.hasErrors()){
            throw  new ValidationPatchException(bindingResult);
        }
    }

    private RestaurantInputDTO mergeDataToPatch(Map<String, Object> restaurantFields,
                                  RestaurantInputDTO restaurantFinal,
                                  HttpServletRequest request) {

        ServletServerHttpRequest servletRequest = new ServletServerHttpRequest(request);

        /*
            Do Jackson (JSON <-> Objetct)
             - faz a criacao / mapeamento de um Objeto (no caso Restaurante) usando com base no MAP passado
         */
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true); // Para lancar Exception quando atributo JSON for ignorado
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true); // Para lancar Exception quando atributo JSON nao existir

            //Restaurant restaurantBase = objectMapper.convertValue(restaurantFields, Restaurant.class);
            RestaurantInputDTO restaurantBase = objectMapper.convertValue(restaurantFields, RestaurantInputDTO.class);

        /*
            O Reflection, em poucas palavras, serve para determinar métodos e atributos que serão utilizados de
            determinada classe (que você nem conhece) em tempo de execução. Há inúmeras utilidades para esse tipo
            de tarefa, podemos citar por exemplo a instalação de plugins adicionais ao nosso software desenvolvido.
         */
            restaurantFields.forEach( (nameAttribute, valueAttribute) -> {

                Field field = ReflectionUtils.findField(RestaurantInputDTO.class, nameAttribute); // Java Reflection
                field.setAccessible(true);

                Object newValueToAttributeInRestaurant = ReflectionUtils.getField(field, restaurantBase);
                ReflectionUtils.setField(field, restaurantFinal, newValueToAttributeInRestaurant);
                //System.out.println(nameAttrinbute + " - " + valueAttribute + " <> " + newValueToAttributeInRestaurant);
            });
        } catch (IllegalArgumentException e){
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletRequest);
        }


        return restaurantFinal;
    }



}
