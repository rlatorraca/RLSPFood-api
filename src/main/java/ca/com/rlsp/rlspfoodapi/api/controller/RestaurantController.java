package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundIntoDBException;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.service.RestauranteRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/restaurants",  produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestaurantController {

    @Autowired
    private RestauranteRegistrationService restauranteRegistrationService;



    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> findBy1Id(@PathVariable("restaurantId") Long id){
        Restaurant restaurant =  restauranteRegistrationService.findById(id);

        //return ResponseEntity.ok(cuisine);
        if(restaurant != null){
            return ResponseEntity.status(HttpStatus.OK).body(restaurant);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restauranteRegistrationService.save(restaurant);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurant);
        } catch (EntityNotFoundIntoDBException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
