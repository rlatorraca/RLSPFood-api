package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import ca.com.rlsp.rlspfoodapi.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class RestaurantOutputDto {

    @JsonView({RestaurantView.Summary.class,
               RestaurantView.SummaryJustName.class})
    private Long id;

    @JsonView({RestaurantView.Summary.class,
            RestaurantView.SummaryJustName.class})
    private String name;

    private BigDecimal deliveryFee;

    @JsonView(RestaurantView.Summary.class)
    private CuisineOutputDto cuisine;

    private AddressOutputDto address;

    @JsonView(RestaurantView.Summary.class)
    private Boolean active;

    @JsonView(RestaurantView.Summary.class)
    private Boolean opened;

    private OffsetDateTime createdDate;

    private OffsetDateTime dateLastUpdate;

}
