package ca.com.rlsp.rlspfoodapi.domain.model.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
@Setter
public class DailySales {
    private OffsetDateTime date;
    private Long amountOfInvoices;
    private BigDecimal totalSold;
}
