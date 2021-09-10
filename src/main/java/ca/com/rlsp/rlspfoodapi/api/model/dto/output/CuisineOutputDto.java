package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import ca.com.rlsp.rlspfoodapi.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuisineOutputDto {

    private Long id;

    @JsonView(RestaurantView.Summary.class)
    private String name;
}
