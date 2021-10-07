package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOutputDto {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Michael Minotauru")
    private String name;

    @ApiModelProperty(example = "minotauro@gmail.com")
    private String email;
}
