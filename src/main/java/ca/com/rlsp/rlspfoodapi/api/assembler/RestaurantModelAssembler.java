package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CuisineInputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.RestaurantInputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.*;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RestaurantModelAssembler {


    /*
        Convert MODEL -> DTO para PUT
    */
    public RestaurantOutputDTO fromControllerToOutput(Restaurant restaurant) {
        CuisineOutputDTO cuisineDTO = new CuisineOutputDTO();
        cuisineDTO.setId(restaurant.getCuisine().getId());
        cuisineDTO.setName(restaurant.getCuisine().getName());

        ProvinceOutputDTO provinceDTO = new ProvinceOutputDTO();
        provinceDTO.setName(restaurant.getAddress().getCity().getProvince().getName());

        CityOutputDTO cityDTO = new CityOutputDTO();
        cityDTO.setName(restaurant.getAddress().getCity().getName());
        cityDTO.setProvince(provinceDTO);

        AddressOutputDTO addressDTO = new AddressOutputDTO();
        addressDTO.setCity(cityDTO);
        addressDTO.setComplement(restaurant.getAddress().getComplement());
        addressDTO.setDistrict(restaurant.getAddress().getDistrict());
        addressDTO.setNumber(restaurant.getAddress().getComplement());
        addressDTO.setPostalcode(restaurant.getAddress().getPostalcode());
        addressDTO.setStreet(restaurant.getAddress().getStreet());


        RestaurantOutputDTO restaurantDTO = new RestaurantOutputDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setDeliveryFee(restaurant.getDeliveryFee());
        restaurantDTO.setCuisine(cuisineDTO);
        restaurantDTO.setAddress(addressDTO);
        restaurantDTO.setCreatedDate(restaurant.getCreatedDate());
        restaurantDTO.setDateLastUpdate(restaurant.getDateLastUpdate());

        return restaurantDTO;
    }

    /*
        Convert MODEL -> DTO (para POST)
    */
    public RestaurantOutputDTO fromControllerToOutputPost(Restaurant restaurant) {
        CuisineOutputDTO cuisineDTO = new CuisineOutputDTO();
        cuisineDTO.setId(restaurant.getCuisine().getId());
        cuisineDTO.setName(restaurant.getCuisine().getName());


        RestaurantOutputDTO restaurantDTO = new RestaurantOutputDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setDeliveryFee(restaurant.getDeliveryFee());
        restaurantDTO.setCuisine(cuisineDTO);
        restaurantDTO.setCreatedDate(restaurant.getCreatedDate());
        restaurantDTO.setDateLastUpdate(restaurant.getDateLastUpdate());

        return restaurantDTO;
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
    public List<RestaurantOutputDTO> fromControllerToOutputList(List<Restaurant> restaurants){
        return restaurants.stream()
                .map(restaurant -> fromControllerToOutput(restaurant))
                .collect(Collectors.toList());
    }
}
