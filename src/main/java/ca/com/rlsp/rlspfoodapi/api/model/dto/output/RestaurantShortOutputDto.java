package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "restaurants_short")
@Setter
@Getter
public class RestaurantShortOutputDto extends RepresentationModel<RestaurantShortOutputDto> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Argentina Bistro")
    private String name;
}
