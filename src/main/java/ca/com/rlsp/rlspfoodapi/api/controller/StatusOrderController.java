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
import ca.com.rlsp.rlspfoodapi.domain.service.StatusOrderRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/orders/{orderId}")
public class StatusOrderController {

    private StatusOrderRegistrationService statusOrderRegistrationService;

    public StatusOrderController(StatusOrderRegistrationService statusOrderRegistrationService) {
        this.statusOrderRegistrationService = statusOrderRegistrationService;
    }

    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable Long orderId) {
        statusOrderRegistrationService.toConfirm(orderId);
    }

}
