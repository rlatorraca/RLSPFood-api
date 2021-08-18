package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class RestaurantInputDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal deliveryFee;

    @Valid
    @NotNull
    private CuisineInputDTO cuisine;



}
