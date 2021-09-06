package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import ca.com.rlsp.rlspfoodapi.api.model.dto.output.*;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderInputDto {

    @Valid
    @NotNull
    private AddressInputDto deliveryAddress;

    @Valid
    @NotNull
    private PaymentTypeIdInputDto paymentType;

    @Valid
    @NotNull
    private RestaurantIdInputDto restaurant;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemOrderInputDto> orderItems;

}
