package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.RestaurantNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.repository.CuisineRepository;
import ca.com.rlsp.rlspfoodapi.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private CityRegistrationService cityRegistrationService;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Long cuisineId = restaurant.getCuisine().getId();
        Long cityId = restaurant.getAddress().getCity().getId();

        Cuisine cuisine = cuisineRegistrationService.findOrFail(cuisineId);
        City city = cityRegistrationService.findOrFail(cityId);

        restaurant.setCuisine(cuisine);
        restaurant.getAddress().setCity(city);

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
            throw new RestaurantNotFoundException(
                    String.format(MSG_RESTAURANT_NOT_SAVED_INTO_THE_DATABASE, id)
            );
        }
    }

    public Restaurant findOrFail(Long id){
        return restaurantRepository.findById(id).orElseThrow(()->
                new RestaurantNotFoundException(String.format(MSG_RESTAURANT_NOT_SAVED_INTO_THE_DATABASE , id))
        );
    }

    @Transactional
    public void activate(Long restauranteId) {
        Restaurant currentRestaurant = findOrFail(restauranteId);

        //currentRestaurant.setActive(true);
        currentRestaurant.activate();
    }

    @Transactional
    public void inactivate(Long restauranteId) {
        Restaurant currentRestaurant = findOrFail(restauranteId);

        //currentRestaurant.setActive(false);
        currentRestaurant.inactivate();
    }

   
}
