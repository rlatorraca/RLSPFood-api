package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.domain.service.StatusOrderRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders/{orderId}")
public class StatusOrderController {

    private StatusOrderRegistrationService statusOrderRegistrationService;

    public StatusOrderController(StatusOrderRegistrationService statusOrderRegistrationService) {
        this.statusOrderRegistrationService = statusOrderRegistrationService;
    }

    @PutMapping("/tp-confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable Long orderId) {
        statusOrderRegistrationService.toConfirm(orderId);
    }

    @PutMapping("/to-start")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void start(@PathVariable Long orderId) {
        statusOrderRegistrationService.toStart(orderId);
    }

    @PutMapping("/to-oven")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void oven(@PathVariable Long orderId) {
        statusOrderRegistrationService.toOnTheOven(orderId);
    }

    @PutMapping("/to-ready")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ready(@PathVariable Long orderId) {
        statusOrderRegistrationService.toReady(orderId);
    }

    @PutMapping("/to-road")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void road(@PathVariable Long orderId) {
        statusOrderRegistrationService.toOnTheRoad(orderId);
    }

    @PutMapping("/to-deliver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliver(@PathVariable Long orderId) {
        statusOrderRegistrationService.toDelivered(orderId);
    }





}
