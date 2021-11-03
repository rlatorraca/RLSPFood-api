package ca.com.rlsp.rlspfoodapi.api.v2.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CuisineInputDtoV2 {


    private Long id;

    @ApiModelProperty(example = "Mediterranean",required = true)
    @NotBlank
    private String name;
}
