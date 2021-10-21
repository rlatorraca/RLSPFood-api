package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.controller.RestaurantController;
import ca.com.rlsp.rlspfoodapi.api.links.BuildLinks;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.RestaurantJustNamesOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestaurantJustNameModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantJustNamesOutputDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildLinks buidLinks;

    public RestaurantJustNameModelAssembler() {
        super(RestaurantController.class, RestaurantJustNamesOutputDto.class);
    }


    @Override
    public RestaurantJustNamesOutputDto toModel(Restaurant restaurant) {
        RestaurantJustNamesOutputDto restaurantJustNamesOutputDto = createModelWithId(restaurant.getId(),restaurant);
        modelMapper.map(restaurant, restaurantJustNamesOutputDto);

        restaurantJustNamesOutputDto.add(buidLinks.getLinkToRestaurants());

        return restaurantJustNamesOutputDto;
    }
}
