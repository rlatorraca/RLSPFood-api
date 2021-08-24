package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressInputDto {

    @NotBlank
    private String postalcode;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    private String complement;

    @NotBlank
    private String district;

    @Valid
    @NotNull
    private CityIdInputDto city;

}
