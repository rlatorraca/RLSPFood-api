package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxProvinceInputDto {

    @ApiModelProperty(example = "1")
    private Long id;


}
