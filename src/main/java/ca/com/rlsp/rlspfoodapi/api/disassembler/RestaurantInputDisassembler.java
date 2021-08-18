package ca.com.rlsp.rlspfoodapi.api.disassembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.RestaurantInputDTO;
import ca.com.rlsp.rlspfoodapi.domain.model.Address;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ModelMapper modelMapperDTO;

    /*
       Comvert DTO -> Model
    */
    public Restaurant fromInputToController(RestaurantInputDTO restaurantInputDTO) {

        /*
        Cuisine cuisine = new Cuisine();
        cuisine.setId(restaurantInputDTO.getCuisine().getId());

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantInputDTO.getName());
        restaurant.setDeliveryFee(restaurantInputDTO.getDeliveryFee());


        restaurant.setCuisine(cuisine);

        return restaurant;
        */
        return modelMapper.map(restaurantInputDTO, Restaurant.class);
    }

    /*
       Mapping DTO -> Model using modelMapper
    */
    public void fromDTOtoRestaurant(RestaurantInputDTO restauranteInputDTO, Restaurant restaurant) {
        // Para evitar org.hibernate.HibernateException: identifier of an instance of
        // com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 2
        restaurant.setCuisine(new Cuisine());
        //restaurant.setAddress(new Address());


        modelMapperDTO.map(restauranteInputDTO, restaurant );


        //return restaurant;
    }

}
