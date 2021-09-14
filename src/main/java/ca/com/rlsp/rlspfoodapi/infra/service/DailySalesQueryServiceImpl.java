package ca.com.rlsp.rlspfoodapi.infra.service;

import ca.com.rlsp.rlspfoodapi.domain.filter.DailySalesFilter;
import ca.com.rlsp.rlspfoodapi.domain.model.statistics.DailySales;
import ca.com.rlsp.rlspfoodapi.domain.service.DailySalesQueryService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DailySalesQueryServiceImpl implements DailySalesQueryService {

    @Override
    public List<DailySales> queryDailySales(DailySalesFilter filter) {
        return null;
    }
}
