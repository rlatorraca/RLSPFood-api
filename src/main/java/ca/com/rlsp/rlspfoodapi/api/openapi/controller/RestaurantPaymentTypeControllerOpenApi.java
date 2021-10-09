package ca.com.rlsp.rlspfoodapi.api.openapi.controller;

import ca.com.rlsp.rlspfoodapi.api.exceptionhandler.ApiHandleProblemDetail;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.RestaurantInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PaymentTypeOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.RestaurantOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.view.RestaurantView;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(tags = "Restaurants")
public interface RestaurantPaymentTypeControllerOpenApi {

    @ApiOperation(value = "Get all payment types by restaurant Id") // Costomize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiHandleProblemDetail.class))
            )
    })
    public List<PaymentTypeOutputDto> listAllByRestaurantId(@ApiParam(value = "Restaurant id", example = "1", required = true)
                                                                          Long restaurantId);

    @ApiOperation("Detach a payment type of a restaurant")  // Customize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Detach successfully done",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiHandleProblemDetail.class))
            ),
            @ApiResponse(responseCode = "404", description = "Restaurant and/or Payment Type not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiHandleProblemDetail.class))
            )
    })
    public void detachPaymentType(@ApiParam(value = "Payment type id", example = "1", required = true)
                                          Long paymentTypeId,
                                  @ApiParam(value = "Restaurant id", example = "1", required = true)
                                          Long restaurantId);


    @ApiOperation("Attach a payment type of a restaurant")  // Customize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Attach successfully done",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiHandleProblemDetail.class))
            ),
            @ApiResponse(responseCode = "404", description = "Restaurant and/or Payment Type not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiHandleProblemDetail.class))
            )
    })
    public void attachPaymentType(@ApiParam(value = "Payment type id", example = "1", required = true)
                                          Long paymentTypeId,
                                  @ApiParam(value = "Restaurant id", example = "1", required = true)
                                          Long restaurantId);
}
