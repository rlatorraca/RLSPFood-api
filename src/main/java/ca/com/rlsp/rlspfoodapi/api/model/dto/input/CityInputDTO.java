package ca.com.rlsp.rlspfoodapi.api.model.dto.input;

import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProvinceOutputDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityInputDTO {

    private Long id;

    private String name;

    private Long province;
}
