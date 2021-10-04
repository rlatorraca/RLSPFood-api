package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GroupOutputDto {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Manager")
    private String name;
}