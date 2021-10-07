package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.util.Json;
import io.swagger.v3.oas.models.media.DateSchema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class OrderOutputDto {


    public  OrderOutputDto() {}

    @ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String orderCode;

    @ApiModelProperty(example = "200.00")
    private BigDecimal beforeTax;

    @ApiModelProperty(example = "20.00")
    private BigDecimal deliveryFee;

    @ApiModelProperty(example = "270.90")
    private BigDecimal afterTax;

    @ApiModelProperty(example = "0.15")
    private BigDecimal taxes;

    @ApiModelProperty(example = "CREATED")
    private String status;

    //@ApiModelProperty(example = "2021-10-01T20:00:00Z")
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

    //private UserOutputDto user;
    private String nameUser;
    private List<ItemOrderOutputDto> orderItems;

}
