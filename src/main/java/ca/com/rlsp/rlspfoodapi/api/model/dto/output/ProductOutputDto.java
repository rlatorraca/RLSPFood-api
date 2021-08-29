package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductOutputDto {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Boolean active;

}
