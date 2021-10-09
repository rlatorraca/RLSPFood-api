package ca.com.rlsp.rlspfoodapi.api.openapi.controller;

import ca.com.rlsp.rlspfoodapi.api.exceptionhandler.ApiHandleProblemDetail;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.PaymentTypeInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PaymentTypeOutputDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Payment Types")
public interface PaymentTypeControllerOpenApi {

    @ApiOperation(value = "List all payment types in JSON") // Customize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cities listed in JSON",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiHandleProblemDetail.class)
                    )
            )
    })
    public ResponseEntity<List<PaymentTypeOutputDto>> listAll(ServletWebRequest request);



    @ApiOperation(value = "Get a Payment Type by ID") // Costomize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid payment type id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiHandleProblemDetail.class))
            ),
            @ApiResponse(responseCode = "404", description = "PAyment type not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiHandleProblemDetail.class))
            )
    })
    public ResponseEntity<PaymentTypeOutputDto> findById(@ApiParam(name = "paymentTypeId", value = "Enter a valid payment type ID", example = "1", required = true)
                                                                     Long paymentTypeId, ServletWebRequest request);

    @ApiOperation(value = "Insert a payment type") // Costomize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Payment type created",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiHandleProblemDetail.class))
            )
    })
    public PaymentTypeOutputDto save(@ApiParam(name = "body", value = "A DTO for inputs a resource of payment type", required =true) PaymentTypeInputDto paymentTypeInputDto);

    @ApiOperation(value = "Update data of a city by ID") // Costomize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "City updated",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiHandleProblemDetail.class))
            ),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class)))
    })
    public PaymentTypeOutputDto update(@ApiParam(name="paymentTypeId" , value= "Enter a valid payment type ID", example = "1", required =true) Long paymentTypeId,
                                       @ApiParam(name = "body", value = "A DTO for inputs a resource of payment type" , required =true)
                                               PaymentTypeInputDto paymentTypeInputDto);


    @ApiOperation("Remove a payment type")  // Customize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Payment type removed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiHandleProblemDetail.class))
            ),
            @ApiResponse(responseCode = "404", description = "Payment type not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiHandleProblemDetail.class))
            )
    })
    public void remove(@ApiParam(name="paymentTypeId" , value= "Enter a valid payment type ID", example = "1", required =true)
                                   Long formaPagamentoId);
}
