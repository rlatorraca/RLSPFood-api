package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.PaymentTypeModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PaymentTypeOutputDto;
import ca.com.rlsp.rlspfoodapi.api.openapi.controller.RestaurantPaymentTypeControllerOpenApi;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.repository.RestaurantRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.RestaurantRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/restaurants/{restaurantId}/paymenttype",  produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestaurantPaymentTypeController implements RestaurantPaymentTypeControllerOpenApi {
    

    private RestaurantRegistrationService restaurantRegistrationService;
    private RestaurantRepository restaurantRepository;
    private PaymentTypeModelAssembler paymentTypeModelAssembler;

    public RestaurantPaymentTypeController(RestaurantRegistrationService restaurantRegistrationService,
                                           RestaurantRepository restaurantRepository,
                                           PaymentTypeModelAssembler paymentTypeModelAssembler) {
        this.restaurantRegistrationService = restaurantRegistrationService;
        this.restaurantRepository = restaurantRepository;
        this.paymentTypeModelAssembler = paymentTypeModelAssembler;
    }

    @GetMapping
    public List<PaymentTypeOutputDto> listAllByRestaurantId(@PathVariable("restaurantId")
                                                          Long id) {
        Restaurant restaurant = restaurantRegistrationService.findOrFail(id);
        List<PaymentTypeOutputDto> paymentTypeOutputDtoList = paymentTypeModelAssembler.fromControllerToOutputList(restaurant.getPaymentTypeList());
        return paymentTypeOutputDtoList;
    }

    @DeleteMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void detachPaymentType(@PathVariable("paymentTypeId") Long paymentTypeId, @PathVariable("restaurantId") Long restaurantId){
        restaurantRegistrationService.detachPaymentType(restaurantId,paymentTypeId);
    }

    @PutMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void attachPaymentType(@PathVariable("paymentTypeId") Long paymentTypeId, @PathVariable("restaurantId") Long restaurantId){
        restaurantRegistrationService.attachPaymentType(restaurantId,paymentTypeId);
    }


}
