package ca.com.rlsp.rlspfoodapi.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Getter
@Setter
public class
OrderFilter {

    private Long userId;
    private Long restaurantId;
    private OffsetDateTime createdDateStart;
    private OffsetDateTime createdDateEnd;
}
