package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.OrderModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.assembler.OrderShortModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.OrderInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.OrderInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.OrderOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.OrderShortOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import ca.com.rlsp.rlspfoodapi.domain.model.User;
import ca.com.rlsp.rlspfoodapi.domain.repository.OrderRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.IssueOfOrderRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private OrderRepository orderRepository;
    private IssueOfOrderRegistrationService issueOfOrderRegistrationService;
    private OrderShortModelAssembler orderShortModelAssembler;
    private OrderModelAssembler orderModelAssembler;
    private OrderInputDisassembler orderInputDisassembler;

    public OrderController(OrderRepository orderRepository,
                           IssueOfOrderRegistrationService issueOfOrderRegistrationService,
                           OrderShortModelAssembler orderShortModelAssembler,
                           OrderModelAssembler orderModelAssembler,
                           OrderInputDisassembler orderInputDisassembler) {
        this.orderRepository = orderRepository;
        this.issueOfOrderRegistrationService = issueOfOrderRegistrationService;
        this.orderShortModelAssembler = orderShortModelAssembler;
        this.orderModelAssembler = orderModelAssembler;
        this.orderInputDisassembler = orderInputDisassembler;
    }

    @GetMapping
    public List<OrderShortOutputDto> listAll() {
        List<Order> allOrders = orderRepository.findAll();

        return orderShortModelAssembler.fromControllerToOutputList(allOrders);
    }

    @GetMapping("/{orderId}")
    public OrderOutputDto find(@PathVariable Long orderId) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderId);

        return orderModelAssembler.fromControllerToOutput(order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderOutputDto add(@Valid @RequestBody OrderInputDto orderInputDto) {
        try {
            Order newOrder = orderInputDisassembler.fromInputToController(orderInputDto);

            // TODO get AUTHENTICATED User
            newOrder.setUser(new User());
            newOrder.getUser().setId(1L);

            newOrder = issueOfOrderRegistrationService.issue(newOrder);

            return orderModelAssembler.fromControllerToOutput(newOrder);
        } catch (EntityNotFoundException e) {
            throw new GenericBusinessException(e.getReason(), e);
        }
    }

}
