package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import ca.com.rlsp.rlspfoodapi.domain.model.TaxProvince;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProvinceInputDto {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Alberta")
    private String name;

    @ApiModelProperty(example = "1")
    private TaxProvinceInputDto tax;
}
