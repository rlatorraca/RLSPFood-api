package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import ca.com.rlsp.rlspfoodapi.domain.model.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class OrderOutputDto {

    private String orderCode;
    private BigDecimal beforeTax;
    private BigDecimal deliveryFee;
    private BigDecimal afterTax;
    private BigDecimal taxes;

    private String status;

    private OffsetDateTime createdDate;
    private OffsetDateTime confirmationDate;
    private OffsetDateTime startedDate;
    private OffsetDateTime onTheOvenDate;
    private OffsetDateTime readyDate;
    private OffsetDateTime onTheRoadDate;
    private OffsetDateTime deliveryDate;
    private OffsetDateTime canceledDate;

    private AddressOutputDto addressDelivery;
    private PaymentTypeOutputDto paymentType;
    private RestaurantShortOutputDto restaurant;

    private UserOutputDto user;
    private List<ItemOrderOutputDto> orderItems;

}
