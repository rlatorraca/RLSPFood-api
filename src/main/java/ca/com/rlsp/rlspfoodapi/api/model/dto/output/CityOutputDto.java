package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityOutputDto {

    private Long id;

    private String name;

    private ProvinceOutputDto province;
}
