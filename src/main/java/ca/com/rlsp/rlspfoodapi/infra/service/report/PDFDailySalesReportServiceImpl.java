package ca.com.rlsp.rlspfoodapi.infra.service.report;

import ca.com.rlsp.rlspfoodapi.domain.filter.DailySalesFilter;
import ca.com.rlsp.rlspfoodapi.domain.service.DailySalesReportService;
import org.springframework.stereotype.Repository;


@Repository
public class PDFDailySalesReportServiceImpl implements DailySalesReportService {

    @Override
    public byte[] issueDailySalesReport(DailySalesFilter filter, String timeOffSet) {
        return new byte[0];
    }
}
