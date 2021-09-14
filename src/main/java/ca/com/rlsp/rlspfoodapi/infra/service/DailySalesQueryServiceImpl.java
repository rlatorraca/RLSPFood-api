package ca.com.rlsp.rlspfoodapi.infra.service;

import ca.com.rlsp.rlspfoodapi.domain.filter.DailySalesFilter;
import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import ca.com.rlsp.rlspfoodapi.domain.model.statistics.DailySales;
import ca.com.rlsp.rlspfoodapi.domain.service.DailySalesQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class DailySalesQueryServiceImpl implements DailySalesQueryService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DailySales> queryDailySales(DailySalesFilter filter) {
        var builder = em.getCriteriaBuilder();
        var query = builder.createQuery(DailySales.class);
        var root = query.from(Order.class);

        var functionToBuildCreatedDate = builder.function("date",
                                                Date.class,
                                                root.get("createdDate"));

        var selection = builder.construct(DailySales.class,
                                                          functionToBuildCreatedDate,
                                                          builder.count(root.get("id")),
                                                          builder.sum(root.get("afterTax")));
        query.select(selection);
        query.groupBy(functionToBuildCreatedDate);

        return em.createQuery(query).getResultList();
    }
}
