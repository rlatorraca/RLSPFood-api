package ca.com.rlsp.rlspfoodapi.api.openapi.model;

import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Link;

import java.util.List;

@ApiModel("CitiesModel")
@Data
public class CitiesModelOpenApi {

    private CityEmbeddedModelOpenApi _embedded;
    private Link _links;


    @ApiModel("CitiesEmbeddedModelOpenApi")
    @Data
    public class CityEmbeddedModelOpenApi  {
        private List<CityOutputDto> cities;

    }
}
