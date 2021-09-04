package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemOrderOutputDto {

    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String comments;
    private Long productId;
    private String productName;
}
