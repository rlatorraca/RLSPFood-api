package ca.com.rlsp.rlspfoodapi.domain.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class DailySalesFilter {

    private Long restaurantId;
    private OffsetDateTime createdDateStart;
    private OffsetDateTime createdDateEnd;
}
