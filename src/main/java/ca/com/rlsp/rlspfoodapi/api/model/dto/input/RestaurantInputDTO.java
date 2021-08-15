package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantInputDTO {

    @NotBlank
    @Column(name="name_restaurant", length = 150, nullable = false)
    private String name;

    @NotNull
    @PositiveOrZero
    @ToString.Exclude
    private BigDecimal deliveryFee;

    @Valid
    @NotNull
    @ToString.Exclude
    private CuisineInputDTO cuisine;
}
