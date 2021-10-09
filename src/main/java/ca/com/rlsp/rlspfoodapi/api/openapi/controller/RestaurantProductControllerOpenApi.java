package ca.com.rlsp.rlspfoodapi.api.openapi.controller;

import ca.com.rlsp.rlspfoodapi.api.exceptionhandler.ApiHandleProblemDetail;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProductInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProductInputUpdateStatusDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.RestaurantInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProductOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.RestaurantOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.view.RestaurantView;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(tags = "Products")
public interface RestaurantProductControllerOpenApi {


    @ApiOperation(value = "List all products") // Customize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid group id",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Group not found",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class)))
    })
    public List<ProductOutputDto> listAllActives(@ApiParam(value = "RestaurantId", example = "1", required = true)
                                                              Long restaurantId,
                                                 @ApiParam(value = "Active or Inactive products on list",
                                                          example = "false", defaultValue = "false")
                                                         boolean justActiveProducts);


    public void detachPaymentType(@PathVariable("paymentTypeId") Long paymentTypeId,
                                  @PathVariable("restaurantId") Long restaurantId);


    @ApiOperation(value = "Get a restaurant product") // Customize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid restaurant id",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant and/or product not found",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class)))
    })
    public ProductOutputDto buscar(@ApiParam(value = "restaurantId", example = "1", required = true)
                                               Long restaurantId,
                                   @ApiParam(value = "productId", example = "1", required = true)
                                           Long productId) ;

    public ProductOutputDto update(@PathVariable Long restaurantId,
                                   @PathVariable Long productId,
                                   @RequestBody  @Valid ProductInputDto productInputDto);;

    public ProductOutputDto updateJustStatus(@PathVariable Long restaurantId,
                                             @PathVariable Long productId,
                                             @RequestBody  @Valid ProductInputUpdateStatusDto productInputDto);


    public ProductOutputDto adicionar(@PathVariable Long restaurantId,
                                      @RequestBody @Valid ProductInputDto productInputDto);

    public void delete(@PathVariable("productId") Long id);
}
