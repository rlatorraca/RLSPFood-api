package ca.com.rlsp.rlspfoodapi.api.v1.controller;

import ca.com.rlsp.rlspfoodapi.api.v1.assembler.PaymentTypeModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.v1.links.BuildLinks;
import ca.com.rlsp.rlspfoodapi.api.v1.model.dto.output.PaymentTypeOutputDto;
import ca.com.rlsp.rlspfoodapi.api.v1.openapi.controller.RestaurantPaymentTypeControllerOpenApi;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.repository.RestaurantRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.RestaurantRegistrationService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/restaurants/{restaurantId}/paymenttype",  produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestaurantPaymentTypeController implements RestaurantPaymentTypeControllerOpenApi {


    private RestaurantRegistrationService restaurantRegistrationService;
    private RestaurantRepository restaurantRepository;
    private PaymentTypeModelAssembler paymentTypeModelAssembler;
    private BuildLinks buildLinks;

    public RestaurantPaymentTypeController(RestaurantRegistrationService restaurantRegistrationService,
                                           RestaurantRepository restaurantRepository,
                                           PaymentTypeModelAssembler paymentTypeModelAssembler,
                                           BuildLinks buildLinks) {
        this.restaurantRegistrationService = restaurantRegistrationService;
        this.restaurantRepository = restaurantRepository;
        this.paymentTypeModelAssembler = paymentTypeModelAssembler;
        this.buildLinks = buildLinks;
    }

    @GetMapping
    //public List<PaymentTypeOutputDto> listAllByRestaurantId(@PathVariable("restaurantId")
    public CollectionModel<PaymentTypeOutputDto> listAllByRestaurantId(@PathVariable("restaurantId")
                                                          Long id) {
        Restaurant restaurant = restaurantRegistrationService.findOrFail(id);
        //List<PaymentTypeOutputDto> paymentTypeOutputDtoList = paymentTypeModelAssembler7.toCollectionModel(restaurant.getPaymentTypeList());
        CollectionModel<PaymentTypeOutputDto> paymentTypeOutputDtoList =
                paymentTypeModelAssembler.toCollectionModel(restaurant.getPaymentTypeList())
                        .removeLinks()
                        .add(buildLinks.getLinkToPaymentTypeOnRestaurants(id))
                        .add(buildLinks.getLinkToPaymentTypeOnRestaurantAttach(id,"attach"));


        // Links for DETACH & ATTACH in Restaurant PaymentType
        paymentTypeOutputDtoList.getContent().forEach( paymentTypeOutput ->{
                paymentTypeOutput
                        .add(buildLinks.getLinkToPaymentTypeOnRestaurantDetach(
                                id,
                                paymentTypeOutput.getId(),
                                "detach"));
            }
        );

        return paymentTypeOutputDtoList;

    }

    @DeleteMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> detachPaymentType(@PathVariable("restaurantId") Long restaurantId, @PathVariable("paymentTypeId") Long paymentTypeId){
        restaurantRegistrationService.detachPaymentType(restaurantId,paymentTypeId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> attachPaymentType(@PathVariable("restaurantId") Long restaurantId, @PathVariable("paymentTypeId") Long paymentTypeId){
        restaurantRegistrationService.attachPaymentType(restaurantId,paymentTypeId);

        return ResponseEntity.noContent().build();
    }


}
