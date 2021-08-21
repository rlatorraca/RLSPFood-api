package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInputDto {

    private String postalcode;

    private String street;

    private String number;

    private String complement;

    private String district;

    private Long city;

}
