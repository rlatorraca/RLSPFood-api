package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.domain.filter.DailySalesFilter;
import ca.com.rlsp.rlspfoodapi.domain.model.statistics.DailySales;
import ca.com.rlsp.rlspfoodapi.domain.service.DailySalesQueryService;
import ca.com.rlsp.rlspfoodapi.domain.service.DailySalesReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private DailySalesQueryService dailySalesQueryService;
    private DailySalesReportService dailySalesReportService;

    public StatisticsController(DailySalesQueryService dailySalesQueryService, DailySalesReportService dailySalesReportService) {
        this.dailySalesQueryService = dailySalesQueryService;
        this.dailySalesReportService = dailySalesReportService;
    }

    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailySales> queryDailySalesJSON(DailySalesFilter dailySalesFilter,
                                            @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet){
        return dailySalesQueryService.queryDailySales(dailySalesFilter, timeOffSet);
    }

    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> queryDailySalesPDF(DailySalesFilter dailySalesFilter,
                                                     @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet){
        byte[] bytesPDF = dailySalesReportService.issueDailySalesReport(dailySalesFilter, timeOffSet);

        var dateNow = OffsetDateTime.now().toLocalDateTime();
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPDF);
    }
}
