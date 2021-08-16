package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import ca.com.rlsp.rlspfoodapi.domain.model.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressOutputDTO {

    private String postalcode;

    private String street;

    private String number;

    private String complement;

    private String district;

    private CityOutputDTO city;

}
