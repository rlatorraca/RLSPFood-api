package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CuisineInputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.RestaurantInputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CuisineOutputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.RestaurantOutputDTO;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CuisineModelAssembler {

    @Autowired
    private ModelMapper modelMapper;


    /*
        Convert MODEL -> DTO para PUT
    */
    public CuisineOutputDTO fromControllerToOutput(Cuisine cuisine) {

        return modelMapper.map(cuisine, CuisineOutputDTO.class); // Mas o mapeamento substituindo o codigo acima
    }


    /*
        Convert MODEL -> DTO (para POST)
    */
    public RestaurantOutputDTO fromControllerToOutputPost(Restaurant restaurant) {

        return modelMapper.map(restaurant, RestaurantOutputDTO.class); // Mas o mapeamento substituindo o codigo acima
    }

    /*
        Convert MODEL -> DTO
    */
    public RestaurantInputDTO fromControllerToInput(Restaurant restaurant) {
        System.out.println(restaurant.getCuisine().getId());
        System.out.println(restaurant.getDeliveryFee());
        System.out.println(restaurant.getName());

        CuisineInputDTO cuisineDTO = new CuisineInputDTO();
        cuisineDTO.setId(restaurant.getCuisine().getId());
        cuisineDTO.setName(restaurant.getCuisine().getName());

        RestaurantInputDTO restaurantDTO = new RestaurantInputDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setDeliveryFee(restaurant.getDeliveryFee());
        restaurantDTO.setCuisine(cuisineDTO);
        return restaurantDTO;
    }


    /*
        Convert MODEL -> DTO (list GET)
    */
    public List<CuisineOutputDTO> fromControllerToOutputList(List<Cuisine> cuisines){
        return cuisines.stream()
                .map(cuisine -> fromControllerToOutput(cuisine))
                .collect(Collectors.toList());
    }
}
