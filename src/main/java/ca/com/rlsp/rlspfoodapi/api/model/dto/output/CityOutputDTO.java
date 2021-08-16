package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityOutputDTO {


    private String name;

    private ProvinceOutputDTO province;
}
