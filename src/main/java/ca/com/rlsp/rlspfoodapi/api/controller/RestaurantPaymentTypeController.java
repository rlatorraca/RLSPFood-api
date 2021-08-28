package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.domain.repository.RestaurantRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.RestaurantRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/restaurants/{restaurantId}/paymenttype",  produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestaurantPaymentTypeController {

    @Autowired
    private RestaurantRegistrationService restaurantRegistrationService;

    @Autowired
    private RestaurantRepository restaurantRepository;


//    @GetMapping
//    public List<PaymentTypeOutputDto> listAll(@PathVariable("restaurantId") Long id) {
//        //return restaurantModelAssembler.fromControllerToOutputList(restaurantRepository.newlistAll());
//        return n;
//    }


}
