package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressOutputDto {

    private String postalcode;

    private String street;

    private String number;

    private String complement;

    private String district;

    private CityOutputDto city;

}
