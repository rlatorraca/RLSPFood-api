package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityShortOutputDto {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(value = "A city name", example = "Calgary")
    private String name;

    @ApiModelProperty(value = "A province name", example = "Alberta")
    private String province;
}
