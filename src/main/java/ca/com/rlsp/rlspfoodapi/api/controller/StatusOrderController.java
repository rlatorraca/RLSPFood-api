package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.domain.service.StatusOrderRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders/{orderCode}")
public class StatusOrderController {

    private StatusOrderRegistrationService statusOrderRegistrationService;

    public StatusOrderController(StatusOrderRegistrationService statusOrderRegistrationService) {
        this.statusOrderRegistrationService = statusOrderRegistrationService;
    }

    @PutMapping("/to-confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable String orderCode) {
        statusOrderRegistrationService.toConfirm(orderCode);
    }

    @PutMapping("/to-start")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void start(@PathVariable String orderCode) {
        statusOrderRegistrationService.toStart(orderCode);
    }

    @PutMapping("/to-cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String orderCode) {
        statusOrderRegistrationService.toCancel(orderCode);
    }

    @PutMapping("/to-oven")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void oven(@PathVariable String orderCode) {
        statusOrderRegistrationService.toOnTheOven(orderCode);
    }

    @PutMapping("/to-ready")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ready(@PathVariable String orderCode) {
        statusOrderRegistrationService.toReady(orderCode);
    }

    @PutMapping("/to-road")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void road(@PathVariable String orderCode) {
        statusOrderRegistrationService.toOnTheRoad(orderCode);
    }

    @PutMapping("/to-delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliver(@PathVariable String orderCode) {
        statusOrderRegistrationService.toDelivered(orderCode);
    }





}
