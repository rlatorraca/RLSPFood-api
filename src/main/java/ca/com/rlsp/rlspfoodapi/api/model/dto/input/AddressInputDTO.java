package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInputDTO {

    private String postalcode;

    private String street;

    private String number;

    private String complement;

    private String district;

    private CityOutputDTO city;

}
