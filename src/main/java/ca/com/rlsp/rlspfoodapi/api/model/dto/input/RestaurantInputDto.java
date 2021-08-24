package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import jdk.jfr.TransitionTo;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantInputDto {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal deliveryFee;

    @Valid
    @NotNull
    private CuisineInputDto cuisine;

    @Valid
    @NotNull
    private AddressInputDto address;



}
