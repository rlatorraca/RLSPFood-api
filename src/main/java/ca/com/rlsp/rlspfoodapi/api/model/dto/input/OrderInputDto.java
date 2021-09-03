package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import ca.com.rlsp.rlspfoodapi.api.model.dto.output.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class OrderInputDto {

    private BigDecimal beforeTax;
    private BigDecimal deliveryFee;
    private BigDecimal afterTax;
    private BigDecimal taxes;

    private String status;

    private OffsetDateTime createdDate;
    private OffsetDateTime confirmationDate;
    private OffsetDateTime modifiedDate;
    private OffsetDateTime cancelDate;
    private OffsetDateTime deliveryDate;

    private AddressInputDto addressDelivery;
    private PaymentTypeInputDto paymentType;
    private RestaurantInputDto restaurant;

    private UserInputDto user;
    private List<ItemOrderOutputDto> orderItems;

}
