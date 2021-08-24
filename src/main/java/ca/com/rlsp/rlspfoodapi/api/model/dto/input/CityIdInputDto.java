package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CityIdInputDto {

    @NotNull
    private Long id;

}
