package ca.com.rlsp.rlspfoodapi.api.v1.assembler;

import ca.com.rlsp.rlspfoodapi.api.v1.controller.RestaurantController;
import ca.com.rlsp.rlspfoodapi.api.v1.links.BuildLinks;
import ca.com.rlsp.rlspfoodapi.api.v1.model.dto.output.RestaurantBasicsOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestaurantBasicsModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantBasicsOutputDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildLinks buidLinks;

    public RestaurantBasicsModelAssembler() {
        super(RestaurantController.class, RestaurantBasicsOutputDto.class);
    }


    @Override
    public RestaurantBasicsOutputDto toModel(Restaurant restaurant) {
        RestaurantBasicsOutputDto restaurantBasicsOutputDto = createModelWithId(restaurant.getId(),restaurant);
        modelMapper.map(restaurant, restaurantBasicsOutputDto);

        restaurantBasicsOutputDto.add(buidLinks.getLinkToRestaurants("restaurants"));

        return restaurantBasicsOutputDto;
    }
}
