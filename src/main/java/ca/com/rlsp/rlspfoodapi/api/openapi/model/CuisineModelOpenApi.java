package ca.com.rlsp.rlspfoodapi.api.openapi.model;

import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CuisineOutputDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@ApiModel("CuisinesModel")

public class CuisineModelOpenApi extends PageModelOpenApi<CuisineOutputDto>{


}
