package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
@Getter
@Setter
public class ItemOrderInputDto {

    @NotNull
    private Long productId;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    private String comments;
    //private BigDecimal unitPrice;
    //private BigDecimal totalPrice;
    //private String productName;
}