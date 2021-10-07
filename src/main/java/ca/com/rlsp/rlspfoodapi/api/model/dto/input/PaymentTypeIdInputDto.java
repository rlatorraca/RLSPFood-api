package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PaymentTypeIdInputDto {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    public Long id;

}
