package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;


/*
    Limitando os campos retornados pela API com @JsonFilter do Jackson
 */
@JsonFilter("orderFilter")
@Setter
@Getter
public class OrderShortOutputDto {

    private String orderCode;
    private BigDecimal beforeTax;
    private BigDecimal deliveryFee;
    private BigDecimal afterTax;
    private BigDecimal taxes;

    private String status;

    private OffsetDateTime createdDate;

    private RestaurantShortOutputDto restaurant;

    private UserOutputDto user;


}
