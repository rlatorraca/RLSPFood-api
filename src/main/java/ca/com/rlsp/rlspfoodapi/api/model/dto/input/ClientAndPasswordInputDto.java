package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ClientAndPasswordInputDto extends ClientInputDto{

    @NotBlank
    private String password;
}
