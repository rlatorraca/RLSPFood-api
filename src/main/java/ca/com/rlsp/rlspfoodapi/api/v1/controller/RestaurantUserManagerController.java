package ca.com.rlsp.rlspfoodapi.api.v1.controller;

import ca.com.rlsp.rlspfoodapi.api.v1.assembler.UserModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.v1.links.BuildLinks;
import ca.com.rlsp.rlspfoodapi.api.v1.model.dto.output.UserOutputDto;
import ca.com.rlsp.rlspfoodapi.api.v1.openapi.controller.RestaurantUserManagerControllerOpenApi;
import ca.com.rlsp.rlspfoodapi.core.security.CheckSecurity;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.service.RestaurantRegistrationService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
//@RequestMapping(path = "/restaurants/{restaurantId}/managers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = "/v1/restaurants/{restaurantId}/managers", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUserManagerController implements RestaurantUserManagerControllerOpenApi {

    private RestaurantRegistrationService restaurantRegistrationService;
    private UserModelAssembler userModelAssembler;

    private BuildLinks buildLinks;

    public RestaurantUserManagerController(RestaurantRegistrationService restaurantRegistrationService,
                                           UserModelAssembler userModelAssembler,
                                           BuildLinks buildLinks) {
        this.restaurantRegistrationService = restaurantRegistrationService;
        this.userModelAssembler = userModelAssembler;
        this.buildLinks = buildLinks;
    }

    @CheckSecurity.Restaurant.hasPermissionToQuery // So pode acessar o metodo se tive permissao de QUERY_RESTAURANT
    @Override
    @GetMapping
    public CollectionModel<UserOutputDto> listOne(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRegistrationService.findOrFail(restaurantId);

        CollectionModel<UserOutputDto> userOutputDtos = userModelAssembler
                .toCollectionModel(restaurant.getManagers())
                .removeLinks()
                .add(buildLinks.getLinkRestaurantManagers(restaurantId))
                .add(buildLinks.getLinkToManagerRestaurantAttach(restaurantId,"attach"));

        return userOutputDtos;
//                .toCollectionModel(restaurant.getManagers())
//                .removeLinks()
//                .add(buildLinks.getLinkToUserGroups(restaurantId));
//                .add(linkTo(methodOn(RestaurantUserManagerController.class)
//                        .listOne(restaurantId))
//                .withSelfRel());
    }

    /*
    @GetMapping
    public List<UserOutputDto> listOne(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRegistrationService.findOrFail(restaurantId);

        return userModelAssembler.fromControllerToOutputList(restaurant.getManagers());
    }
    */

    @CheckSecurity.Restaurant.hasPermissionToEdit // So pode acessar o metodo se tive permissao de EDIT_RESTAURANT
    @Override
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> detachManager(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantRegistrationService.detachManager(restaurantId, userId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurant.hasPermissionToEdit // So pode acessar o metodo se tive permissao de EDIT_RESTAURANT
    @Override
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> attachManager(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantRegistrationService.attachManager(restaurantId, userId);
        return ResponseEntity.noContent().build();
    }
}
