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

    @Autowired
    private IssueOfOrderRegistrationService issueOfOrderRegistrationService;

    @Transactional
    public void toConfirm(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);
        order.confirm();
    }

    @Transactional
    public void toCancel(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);
        order.cancel();
    }

    @Transactional
    public void toStart(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);
        order.start();
    }

    @Transactional
    public void toOnTheOven(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);
        order.onTheOven();
    }

    @Transactional
    public void toReady(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);
        order.ready();
    }

    @Transactional
    public void toOnTheRoad(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);
        order.ontTheRoad();
    }

    @Transactional
    public void toDelivered(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);
        order.delivered();
    }
}
