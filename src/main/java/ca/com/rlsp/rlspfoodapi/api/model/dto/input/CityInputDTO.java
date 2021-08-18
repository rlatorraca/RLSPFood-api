package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProvinceOutputDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CityInputDTO {

    private Long id;

    @NotBlank
    private String name;

    @Valid
    @NotNull
    private ProvinceInputDTO province;
}
