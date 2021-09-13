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
    public void toConfirm(String orderCode) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderCode);
        order.confirm();
    }

    @Transactional
    public void toCancel(String orderCode) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderCode);
        order.cancel();
    }

    @Transactional
    public void toStart(String orderCode) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderCode);
        order.start();
    }

    @Transactional
    public void toOnTheOven(String orderCode) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderCode);
        order.onTheOven();
    }

    @Transactional
    public void toReady(String orderCode) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderCode);
        order.ready();
    }

    @Transactional
    public void toOnTheRoad(String orderCode) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderCode);
        order.ontTheRoad();
    }

    @Transactional
    public void toDelivered(String orderCode) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderCode);
        order.delivered();
    }
}