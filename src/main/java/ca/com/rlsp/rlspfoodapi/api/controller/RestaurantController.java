package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.repository.RestaurantRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.RestaurantRegistrationService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/restaurants",  produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestaurantController {

    @Autowired
    private RestaurantRegistrationService restaurantRegistrationService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public List<Restaurant> listAll() {
        return restaurantRepository.newlistAll();
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
    public Restaurant findBy1Id(@PathVariable("restaurantId") Long id){
        return restaurantRegistrationService.findOrFail(id);
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
    public Restaurant save(@RequestBody Restaurant restaurant) {
       try {
           return restaurantRegistrationService.save(restaurant);
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
    public Restaurant updateById(@PathVariable("restauranteId") Long id, @RequestBody Restaurant restaurant) {
        Restaurant currentRestaurant = restaurantRegistrationService.findOrFail(id);
        BeanUtils.copyProperties(restaurant, currentRestaurant,"id", "paymentTypeList", "address", "createdDate", "products");
        try {
            return restaurantRegistrationService.save(currentRestaurant);
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
    public Restaurant updateByIdPatch(@PathVariable("restauranteId") Long id,
                                      @RequestBody Map<String, Object> restaurantFields,
                                      HttpServletRequest request){
        Restaurant currentRestaurant = restaurantRegistrationService.findOrFail(id);

        mergeDataToPatch(restaurantFields, currentRestaurant, request);
        return updateById(id, currentRestaurant);
    }

    private Restaurant mergeDataToPatch(Map<String, Object> restaurantFields,
                                  Restaurant restaurantFinal,
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

            Restaurant restaurantBase = objectMapper.convertValue(restaurantFields, Restaurant.class);

        /*
            O Reflection, em poucas palavras, serve para determinar métodos e atributos que serão utilizados de
            determinada classe (que você nem conhece) em tempo de execução. Há inúmeras utilidades para esse tipo
            de tarefa, podemos citar por exemplo a instalação de plugins adicionais ao nosso software desenvolvido.
         */
            restaurantFields.forEach( (nameAttrinbute, valueAttribute) -> {

                Field field = ReflectionUtils.findField(Restaurant.class, nameAttrinbute); // Java Reflection
                field.setAccessible(true);

                Object newValueToAttributeInRestaurant = ReflectionUtils.getField(field, restaurantBase);
                ReflectionUtils.setField(field, restaurantFinal, newValueToAttributeInRestaurant);
                System.out.println(nameAttrinbute + " - " + valueAttribute + " <> " + newValueToAttributeInRestaurant);
            });
        } catch (IllegalArgumentException e){
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletRequest);
        }


        return restaurantFinal;
    }


}
