package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import ca.com.rlsp.rlspfoodapi.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuisineOutputDto {

    @ApiModelProperty(example = "1")
    @JsonView(RestaurantView.Summary.class)
    private Long id;

    @ApiModelProperty(example = "Brasileira")
    @JsonView(RestaurantView.Summary.class)
    private String name;
}
