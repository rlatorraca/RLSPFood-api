package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import ca.com.rlsp.rlspfoodapi.domain.model.Product;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class ItemOrderOutputDto {

    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String comments;
    private Long productId;
    private String productName;
}
