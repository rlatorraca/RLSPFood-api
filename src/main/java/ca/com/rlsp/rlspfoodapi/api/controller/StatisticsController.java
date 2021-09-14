package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.domain.filter.DailySalesFilter;
import ca.com.rlsp.rlspfoodapi.domain.model.statistics.DailySales;
import ca.com.rlsp.rlspfoodapi.domain.service.DailySalesQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private DailySalesQueryService dailySalesQueryService;

    public StatisticsController(DailySalesQueryService dailySalesQueryService) {
        this.dailySalesQueryService = dailySalesQueryService;
    }

    @GetMapping("/daily-sales")
    public List<DailySales> queryDailySales(DailySalesFilter dailySalesFilter){
        return dailySalesQueryService.queryDailySales(dailySalesFilter);
    }
}
