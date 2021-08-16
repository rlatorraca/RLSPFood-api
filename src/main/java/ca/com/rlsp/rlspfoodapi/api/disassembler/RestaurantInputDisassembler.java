package ca.com.rlsp.rlspfoodapi.api.disassembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.RestaurantInputDTO;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

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

}
