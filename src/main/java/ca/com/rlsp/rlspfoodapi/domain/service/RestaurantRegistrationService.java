package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundIntoDBException;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.repository.CuisineRepository;
import ca.com.rlsp.rlspfoodapi.domain.repository.RestaurantRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantRegistrationService {

    public static final String MSG_RESTAURANT_NOT_SAVED_INTO_THE_DATABASE = "Restaurant as code is %d not saved into the Database";
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CuisineRegistrationService cuisineRegistrationService;

    public Restaurant save(Restaurant restaurant) {
        Long cuisineId = restaurant.getCuisine().getId();
        Cuisine cuisine = cuisineRegistrationService.findOrFail(cuisineId);

        restaurant.setCuisine(cuisine);

        return restaurantRepository.save(restaurant);
    }
    /*
    public Restaurant save(Restaurant restaurant){
        Long cuisineId = restaurant.getCuisine().getId();
        Cuisine cuisine = cuisineRepository
                .findById(cuisineId)
                .orElseThrow(
                        () -> new EntityNotFoundIntoDBException(
                                String.format(MSG_RESTAURANT_NOT_SAVED_INTO_THE_DATABASE, cuisineId)
                ));

        restaurant.setCuisine(cuisine);
        return restaurantRepository.save(restaurant);
    }
    */

    public List<Restaurant> listAll(){
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> findById(Long id){
        try{
            return  restaurantRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundIntoDBException(
                    String.format(MSG_RESTAURANT_NOT_SAVED_INTO_THE_DATABASE, id)
            );
        }
    }

    public Restaurant findOrFail(Long id){
        return restaurantRepository.findById(id).orElseThrow(()->
                new EntityNotFoundIntoDBException(String.format(MSG_RESTAURANT_NOT_SAVED_INTO_THE_DATABASE , id))
        );
    }
}
