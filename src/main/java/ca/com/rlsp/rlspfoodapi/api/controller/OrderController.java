package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.OrderModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.OrderOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import ca.com.rlsp.rlspfoodapi.domain.repository.OrderRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.IssuanceOfOrderRegistrationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private OrderRepository orderRepository;
    private IssuanceOfOrderRegistrationService issuanceOfOrderRegistrationService;
    private OrderModelAssembler orderModelAssembler;

    public OrderController(OrderRepository orderRepository,
                           IssuanceOfOrderRegistrationService issuanceOfOrderRegistrationService,
                           OrderModelAssembler orderModelAssembler) {
        this.orderRepository = orderRepository;
        this.issuanceOfOrderRegistrationService = issuanceOfOrderRegistrationService;
        this.orderModelAssembler = orderModelAssembler;
    }

    @GetMapping
    public List<OrderOutputDto> listAll() {
        List<Order> allOrders = orderRepository.findAll();

        return orderModelAssembler.fromControllerToOutputList(allOrders);
    }

    @GetMapping("/{orderId}")
    public OrderOutputDto find(@PathVariable Long orderId) {
        Order order = issuanceOfOrderRegistrationService.findOrFail(orderId);

        return orderModelAssembler.fromControllerToOutput(order);
    }

}
