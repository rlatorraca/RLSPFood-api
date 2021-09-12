package ca.com.rlsp.rlspfoodapi.api.model.dto.input.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderFilterInputDto {

    private Long userId;
    private Long restaurantId;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime createdDateStart;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime createdDateEnd;
}
