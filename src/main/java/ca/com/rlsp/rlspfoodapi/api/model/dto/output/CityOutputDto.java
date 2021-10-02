package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "City", description = "Output to City representation")
@Getter
@Setter
public class CityOutputDto {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(value = "A name to the city", example = "Calgary")
    private String name;

    private ProvinceOutputDto province;
}
