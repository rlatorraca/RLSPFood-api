package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantOutputDTO {

    private Long id;

    private String name;

    private BigDecimal deliveryFee;

    private CuisineOutputDTO cuisine;
}
