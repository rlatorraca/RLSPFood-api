package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.domain.filter.DailySalesFilter;
import ca.com.rlsp.rlspfoodapi.domain.model.statistics.DailySales;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DailySalesQueryService {

    public List<DailySales> queryDailySales(DailySalesFilter filter);
}
