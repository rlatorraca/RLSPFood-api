package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductOutputDto {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Pizza Pantaneira", required = true)
    private String name;

    @ApiModelProperty(example = "Cheese, olive, salted meat, banana, pea", required = true)
    private String description;

    @ApiModelProperty(example = "25.00", required = true)
    private BigDecimal price;

    @ApiModelProperty(example = "true", required = true)
    private Boolean active;

}
