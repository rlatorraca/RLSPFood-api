package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class RestaurantOutputDto {

    private Long id;

    private String name;

    private BigDecimal deliveryFee;

    private CuisineOutputDto cuisine;

    private AddressOutputDto address;

    private Boolean active;

    private OffsetDateTime createdDate;

    private OffsetDateTime dateLastUpdate;

}
