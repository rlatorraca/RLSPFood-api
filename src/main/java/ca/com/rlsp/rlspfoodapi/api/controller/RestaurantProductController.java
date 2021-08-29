package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.PaymentTypeModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.assembler.ProductModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.ProductInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProductInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PaymentTypeOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProductOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.repository.ProductRepository;
import ca.com.rlsp.rlspfoodapi.domain.repository.RestaurantRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.ProductRegistrationService;
import ca.com.rlsp.rlspfoodapi.domain.service.RestaurantRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/restaurants/{restaurantId}/products",  produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestaurantProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRegistrationService productRegistrationService;

    @Autowired
    private RestaurantRegistrationService restaurantRegistrationService;

    @Autowired
    private ProductModelAssembler productModelAssembler;
    @Autowired
    private ProductInputDisassembler productInputDisassembler;


    @GetMapping
    public List<ProductOutputDto> listAll(@PathVariable("restaurantId")
                                                          Long id) {
        Restaurant restaurant = restaurantRegistrationService.findOrFail(id);
        List<ProductOutputDto> productOutputDtoList = productModelAssembler.fromControllerToOutputList(restaurant.getProducts());
        return productOutputDtoList;
    }

    @DeleteMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void detachPaymentType(@PathVariable("paymentTypeId") Long paymentTypeId, @PathVariable("restaurantId") Long restaurantId){
        restaurantRegistrationService.detachPaymentType(restaurantId,paymentTypeId);
    }

    @GetMapping("/{produtoId}")
    public ProductOutputDto buscar(@PathVariable Long restaurantId, @PathVariable Long productId) {
        Product product = productRegistrationService.findOrFail(restaurantId, productId);

        return productModelAssembler.fromControllerToOutput(product);
    }

    /*
    @PutMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void attachPaymentType(@PathVariable Long paymentTypeId, @PathVariable Long restaurantId){
        restaurantRegistrationService.attachPaymentType(restaurantId,paymentTypeId);
    }
    */

    @PutMapping("/{produtoId}")
    public ProductOutputDto update(@PathVariable Long restaurantId, @PathVariable Long productId,
                                  @RequestBody @Valid ProductInputDto productInputDto) {
        Product currentProduct = productRegistrationService.findOrFail(restaurantId, productId);

        productInputDisassembler.fromDTOtoProduct(productInputDto, currentProduct);

        currentProduct = productRegistrationService.save(currentProduct);

        return productModelAssembler.fromControllerToOutput(currentProduct);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductOutputDto adicionar(@PathVariable Long restaurantId,
                                  @RequestBody @Valid ProductInputDto productInputDto) {
        Restaurant restaurant =  restaurantRegistrationService.findOrFail(restaurantId);

        Product product = productInputDisassembler.fromInputToController(productInputDto);
        product.setRestaurant(restaurant);

        product = productRegistrationService.save(product);

        return productModelAssembler.fromControllerToOutput(product);
    }


}
