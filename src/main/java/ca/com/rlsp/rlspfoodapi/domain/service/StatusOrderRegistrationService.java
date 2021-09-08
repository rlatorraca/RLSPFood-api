package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import ca.com.rlsp.rlspfoodapi.domain.model.StatusOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class StatusOrderRegistrationService {

    public static final String MSG_STATUS_ORDER_CANNOT_BE_CONFIRMED="Order Status %d cannot be changed from %s to %s";

    @Autowired
    private IssueOfOrderRegistrationService issueOfOrderRegistrationService;

    @Transactional
    public void toConfirm(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);

        if(!(order.getStatus().equals(StatusOrderEnum.CREATED))) {
            throw new GenericBusinessException(
                    String.format(
                            MSG_STATUS_ORDER_CANNOT_BE_CONFIRMED,
                            order.getId(),
                            order.getStatus().getDescription(),
                            StatusOrderEnum.CONFIRMED.getDescription())
            );
        }

        order.setStatus(StatusOrderEnum.CONFIRMED);
        order.setConfirmationDate(OffsetDateTime.now());
    }
}
