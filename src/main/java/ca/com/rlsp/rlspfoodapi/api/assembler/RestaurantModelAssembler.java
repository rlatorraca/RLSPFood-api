package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CuisineInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.RestaurantInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.*;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantModelAssembler {

    @Autowired
    private ModelMapper modelMapper;


    /*
        Convert MODEL -> DTO para PUT
    */
    public RestaurantOutputDto fromControllerToOutput(Restaurant restaurant) {
        /*
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
         */

        return modelMapper.map(restaurant, RestaurantOutputDto.class); // Mas o mapeamento substituindo o codigo acima
    }


    /*
        Convert MODEL -> DTO (para POST)
    */
    public RestaurantOutputDto fromControllerToOutputPost(Restaurant restaurant) {
        /*
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
         */
        return modelMapper.map(restaurant, RestaurantOutputDto.class); // Mas o mapeamento substituindo o codigo acima
    }

    /*
        Convert MODEL -> DTO
    */
    public RestaurantInputDto fromControllerToInput(Restaurant restaurant) {
        System.out.println(restaurant.getCuisine().getId());
        System.out.println(restaurant.getDeliveryFee());
        System.out.println(restaurant.getName());

        CuisineInputDto cuisineDTO = new CuisineInputDto();
        //cuisineDTO.setId(restaurant.getCuisine().getId());
        cuisineDTO.setName(restaurant.getCuisine().getName());

        RestaurantInputDto restaurantDTO = new RestaurantInputDto();
        //restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setDeliveryFee(restaurant.getDeliveryFee());
        restaurantDTO.setCuisine(cuisineDTO);
        return restaurantDTO;
    }


    /*
        Convert MODEL -> DTO (list GET)
    */
    public List<RestaurantOutputDto> fromControllerToOutputList(List<Restaurant> restaurants){
        return restaurants.stream()
                .map(restaurant -> fromControllerToOutput(restaurant))
                .collect(Collectors.toList());
    }
}
