package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import ca.com.rlsp.rlspfoodapi.api.model.dto.output.*;
import com.sun.istack.NotNull;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class OrderInputDto {

    @Valid
    @NotNull
    private AddressInputDto addressDelivery;

    @Valid
    @NotNull
    private PaymentTypeInputDto paymentType;

    @Valid
    @NotNull
    private RestaurantInputDto restaurant;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemOrderOutputDto> orderItems;

}
