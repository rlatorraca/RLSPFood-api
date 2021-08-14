package ca.com.rlsp.rlspfoodapi.api.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantDTO {

    private Long id;

    private String name;

    private BigDecimal deliveryFee;

    private CuisineDTO cuisine;
}
