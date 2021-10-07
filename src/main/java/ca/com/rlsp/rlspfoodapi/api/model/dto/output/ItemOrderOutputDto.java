package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemOrderOutputDto {

    @ApiModelProperty(example = "1")
    private Integer quantity;
    @ApiModelProperty(example = "80.00")
    private BigDecimal unitPrice;
    @ApiModelProperty(example = "105.00")
    private BigDecimal totalPrice;
    @ApiModelProperty(example = "No spicy")
    private String comments;
    @ApiModelProperty(example = "1")
    private Long productId;
    @ApiModelProperty(example = "Cheese poutine")
    private String productName;
}
