package ca.com.rlsp.rlspfoodapi.api.controller.openapi;

import ca.com.rlsp.rlspfoodapi.api.exceptionhandler.ApiHandleProblemDetail;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.exception.ProvinceNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cities")
public interface CityControllerOpenApi {
    @ApiOperation(value = "List all cities in JSON") // Costomize method description on SwaggerUI
    public List<CityOutputDto> listAllJson();

    @ApiOperation(value = "List all cities in XML") // Costomize method description on SwaggerUI
    public List<City> listAllXml();


    @ApiOperation(value = "Get a City by ID") // Costomize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid city id",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class)))
    })
    public CityOutputDto findById(@ApiParam(name = "cityId", value = "Enter a valid city ID", example = "1", required = true)
                                  Long cityId);

    @ApiOperation(value = "Insert a city") // Costomize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "City created",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class)))
    })
    public CityOutputDto save(@ApiParam(name = "body", value = "A DTO for inputs a resource of city")
                              CityInputDto cityInputDTO);

    @ApiOperation(value = "Update data of a city by ID") // Costomize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "City updated",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class)))
    })
    public CityOutputDto updateById(@ApiParam(name="cityId" , value= "Enter a valid city ID", example = "1", required =true)
                                    Long id,
                                    CityInputDto cityInputDTO);


    @ApiOperation("Remove a city")  // Customize method description on SwaggerUI
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "City removed",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content(schema = @Schema(implementation = ApiHandleProblemDetail.class)))
    })
    public void delete(@ApiParam(name="cityId" , value = "Enter a valid city ID", example ="1") Long id);

}
