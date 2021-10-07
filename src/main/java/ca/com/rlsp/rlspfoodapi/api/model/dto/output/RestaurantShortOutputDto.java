package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantShortOutputDto {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Argentina Bistro")
    private String name;
}
