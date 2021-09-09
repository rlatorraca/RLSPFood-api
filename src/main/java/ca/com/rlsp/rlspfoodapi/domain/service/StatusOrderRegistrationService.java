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

    public static final String MSG_STATUS_ORDER_CANNOT_BE_CONFIRMED="Order Status %d cannot be modified from %s to %s";
    public static final String MSG_STATUS_ORDER_CANNOT_BE_CANCELED="Order Status %d cannot be canceled from %s to %s";
    public static final String MSG_STATUS_ORDER_CANNOT_BE_STARTED="Order Status %d cannot be started from %s to %s";
    public static final String MSG_STATUS_ORDER_CANNOT_BE_ON_THE_OVEN="Order Status %d cannot be on the oven from %s to %s";
    public static final String MSG_STATUS_ORDER_CANNOT_BE_TO_READY="Order Status %d cannot be ready from %s to %s";
    public static final String MSG_STATUS_ORDER_CANNOT_BE_ON_THE_ROAD="Order Status %d cannot be on the road from %s to %s";
    public static final String MSG_STATUS_ORDER_CANNOT_BE_DELIVERED="Order Status %d cannot be delivered from %s to %s";

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

    @Transactional
    public void toCancel(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);

        if(!(order.getStatus().equals(StatusOrderEnum.CONFIRMED))) {
            throw new GenericBusinessException(
                    String.format(
                            MSG_STATUS_ORDER_CANNOT_BE_CANCELED,
                            order.getId(),
                            order.getStatus().getDescription(),
                            StatusOrderEnum.CANCELED.getDescription())
            );
        }

        order.setStatus(StatusOrderEnum.CANCELED);
        order.setConfirmationDate(OffsetDateTime.now());
    }

    @Transactional
    public void toStart(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);

        if(!(order.getStatus().equals(StatusOrderEnum.CONFIRMED))) {
            throw new GenericBusinessException(
                    String.format(
                            MSG_STATUS_ORDER_CANNOT_BE_STARTED,
                            order.getId(),
                            order.getStatus().getDescription(),
                            StatusOrderEnum.STARTED.getDescription())
            );
        }

        order.setStatus(StatusOrderEnum.STARTED);
        order.setConfirmationDate(OffsetDateTime.now());
    }

    @Transactional
    public void toOnTheOven(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);

        if(!(order.getStatus().equals(StatusOrderEnum.STARTED))) {
            throw new GenericBusinessException(
                    String.format(
                            MSG_STATUS_ORDER_CANNOT_BE_ON_THE_OVEN,
                            order.getId(),
                            order.getStatus().getDescription(),
                            StatusOrderEnum.ON_THE_OVEN.getDescription())
            );
        }

        order.setStatus(StatusOrderEnum.ON_THE_OVEN);
        order.setConfirmationDate(OffsetDateTime.now());
    }

    @Transactional
    public void toReady(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);

        if(!(order.getStatus().equals(StatusOrderEnum.ON_THE_OVEN))) {
            throw new GenericBusinessException(
                    String.format(
                            MSG_STATUS_ORDER_CANNOT_BE_TO_READY,
                            order.getId(),
                            order.getStatus().getDescription(),
                            StatusOrderEnum.READY.getDescription())
            );
        }

        order.setStatus(StatusOrderEnum.READY);
        order.setConfirmationDate(OffsetDateTime.now());
    }

    @Transactional
    public void toOnTheRoad(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);

        if(!(order.getStatus().equals(StatusOrderEnum.READY))) {
            throw new GenericBusinessException(
                    String.format(
                            MSG_STATUS_ORDER_CANNOT_BE_ON_THE_ROAD,
                            order.getId(),
                            order.getStatus().getDescription(),
                            StatusOrderEnum.ON_THE_ROAD.getDescription())
            );
        }

        order.setStatus(StatusOrderEnum.ON_THE_ROAD);
        order.setConfirmationDate(OffsetDateTime.now());
    }

    @Transactional
    public void toDelivered(Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);

        if(!(order.getStatus().equals(StatusOrderEnum.ON_THE_ROAD))) {
            throw new GenericBusinessException(
                    String.format(
                            MSG_STATUS_ORDER_CANNOT_BE_DELIVERED,
                            order.getId(),
                            order.getStatus().getDescription(),
                            StatusOrderEnum.DELIVERED.getDescription())
            );
        }

        order.setStatus(StatusOrderEnum.DELIVERED);
        order.setConfirmationDate(OffsetDateTime.now());
    }
}
