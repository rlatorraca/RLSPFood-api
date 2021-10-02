package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProvinceOutputDto {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Alberta")
    private String name;
}
