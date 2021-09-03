package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.domain.exception.OrderNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import ca.com.rlsp.rlspfoodapi.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class IssuanceOfOrderRegistrationService {

    @Autowired
    private OrderRepository orderRepository;

    public Order findOrFail(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}
