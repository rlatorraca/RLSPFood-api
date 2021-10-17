package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "city_short")
@ApiModel(value = "Short City")
@Getter
@Setter
public class CityShortOutputDto extends RepresentationModel<CityShortOutputDto> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(value = "A city name", example = "Calgary")
    private String name;

    @ApiModelProperty(value = "A province name", example = "Alberta")
    private String province;
}
