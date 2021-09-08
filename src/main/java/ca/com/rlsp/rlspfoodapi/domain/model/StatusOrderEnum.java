package ca.com.rlsp.rlspfoodapi.domain.model;

import lombok.Getter;

@Getter
public enum StatusOrderEnum {

    CREATED("Order Created"),
    CONFIRMED_START("Order Started"),
    CONFIRMED_OVEN("Order into oven"),
    CONFIRMED_READY("Order ready to go"),
    ON_THE_ROAD("Order on the road"),
    DELIVERED("Order Delivered"),
    CANCELED("Order Canceled");

    private String description;

    StatusOrderEnum(String description) {
        this.description = description;
    }
}
