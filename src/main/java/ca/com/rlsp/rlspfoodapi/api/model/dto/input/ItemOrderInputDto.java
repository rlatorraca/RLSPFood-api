package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import java.math.BigDecimal;

public class ItemOrderInputDto {

    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String comments;
    private Long productId;
    private String productName;
}
