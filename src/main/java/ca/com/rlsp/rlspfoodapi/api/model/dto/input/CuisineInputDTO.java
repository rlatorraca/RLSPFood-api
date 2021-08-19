package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CuisineInputDTO {


    private Long id;

    //@NotBlank
    private String name;
}
