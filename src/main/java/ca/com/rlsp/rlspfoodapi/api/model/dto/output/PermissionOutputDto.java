package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionOutputDto {

    @ApiModelProperty(example = "1")
    private String id;

    @ApiModelProperty(example = "FULL CLIENT")
    private String name;

    @ApiModelProperty(example = "Full access in all client resources")
    private String description;

}
