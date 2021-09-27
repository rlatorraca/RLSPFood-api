package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import ca.com.rlsp.rlspfoodapi.domain.model.StatusOrderEnum;
import ca.com.rlsp.rlspfoodapi.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class StatusOrderRegistrationService {

    @Autowired
    private IssueOfOrderRegistrationService issueOfOrderRegistrationService;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void toConfirm(String orderCode) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderCode);
        order.confirm();

        /*
            Mesmo salvando a ORDER por meio do SERVICE usamos o REPOSITORY oara que o Spring JPA funcione os EVENTOS
            disparados pelo Spring JPA
            - Disprando assim os eventos que estao na fila
         */
        orderRepository.save(order);

        var message = SendEmailService.Message.builder()
                .subject(order.getRestaurant().getName() + "- Order Confirmed")
                .body("order-confirmed.html")
                .templateAttribute("order", order)
                .destination(order.getUser().getEmail())
                .build();

        sendEmailService.send(message);
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
