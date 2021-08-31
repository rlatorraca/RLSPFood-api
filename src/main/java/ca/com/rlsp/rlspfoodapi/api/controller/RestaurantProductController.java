package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.ProductModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.ProductInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProductInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProductInputUpdateStatusDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProductOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.repository.ProductRepository;
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

    private ProductRepository productRepository;
    private ProductRegistrationService productRegistrationService;
    private RestaurantRegistrationService restaurantRegistrationService;
    private ProductModelAssembler productModelAssembler;
    private ProductInputDisassembler productInputDisassembler;

    public RestaurantProductController(ProductRepository productRepository,
                                       ProductRegistrationService productRegistrationService,
                                       RestaurantRegistrationService restaurantRegistrationService,
                                       ProductModelAssembler productModelAssembler,
                                       ProductInputDisassembler productInputDisassembler) {

        this.productRepository = productRepository;
        this.productRegistrationService = productRegistrationService;
        this.restaurantRegistrationService = restaurantRegistrationService;
        this.productModelAssembler = productModelAssembler;
        this.productInputDisassembler = productInputDisassembler;
    }

    @GetMapping
    public List<ProductOutputDto> listAll(@PathVariable("restaurantId") Long id) {
        Restaurant restaurant = restaurantRegistrationService.findOrFail(id);
        List<ProductOutputDto> productOutputDtoList = productModelAssembler.fromControllerToOutputList(restaurant.getProducts());
        return productOutputDtoList;
    }

    @DeleteMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void detachPaymentType(@PathVariable("paymentTypeId") Long paymentTypeId,
                                  @PathVariable("restaurantId") Long restaurantId){
        restaurantRegistrationService.detachPaymentType(restaurantId,paymentTypeId);
    }

    @GetMapping("/{productId}")
    public ProductOutputDto buscar(@PathVariable Long restaurantId,
                                   @PathVariable Long productId) {
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

    @PutMapping("/{productId}")
    public ProductOutputDto update(@PathVariable Long restaurantId,
                                   @PathVariable Long productId,
                                   @RequestBody  @Valid ProductInputDto productInputDto) {
        return getProductOutputDto(restaurantId, productId, productInputDto);
    }

    @PutMapping("/{productId}/status")
    public ProductOutputDto updateJustStatus(@PathVariable Long restaurantId,
                                   @PathVariable Long productId,
                                   @RequestBody  @Valid ProductInputUpdateStatusDto productInputDto) {
        return getProductOutputDto(restaurantId, productId, productInputDto);
    }

    private ProductOutputDto getProductOutputDto(Long restaurantId, Long productId, ProductInputUpdateStatusDto productInputDto) {
        try{
            Product currentProduct = productRegistrationService.findOrFail(restaurantId, productId);

            if(productInputDto instanceof ProductInputDto){
                productInputDisassembler.fromDTOtoProduct(productInputDto, currentProduct);
            } else {
                productInputDisassembler.fromDTOtoProductStatus(productInputDto, currentProduct);
            }

            currentProduct = productRegistrationService.save(currentProduct);

        return productModelAssembler.fromControllerToOutput(currentProduct);
        } catch ( EntityNotFoundException e ){
            throw new GenericBusinessException(e.getReason());
        }
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

    @DeleteMapping("/delete/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("productId") Long id) {
        productRegistrationService.remove(id);
    }
}
