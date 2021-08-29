package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductInputUpdateStatusDto {


    @NotNull
    @Column(name = "product_active", nullable = false )
    private Boolean active;
}
