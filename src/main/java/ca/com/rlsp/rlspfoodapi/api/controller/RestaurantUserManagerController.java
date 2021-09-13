package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.UserModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.UserInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.UserOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.service.RestaurantRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/managers")
public class RestaurantUserManagerController {

    private RestaurantRegistrationService restaurantRegistrationService;
    private UserModelAssembler userModelAssembler;

    public RestaurantUserManagerController(RestaurantRegistrationService restaurantRegistrationService,
                                           UserModelAssembler userModelAssembler) {
        this.restaurantRegistrationService = restaurantRegistrationService;
        this.userModelAssembler = userModelAssembler;
    }

    @GetMapping
    public List<UserOutputDto> listOne(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRegistrationService.findOrFail(restaurantId);

        return userModelAssembler.fromControllerToOutputList(restaurant.getManagers());
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void detachManager(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantRegistrationService.detachManager(restaurantId, userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void attachManager(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantRegistrationService.attachManager(restaurantId, userId);
    }
}