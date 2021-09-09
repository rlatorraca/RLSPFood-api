package ca.com.rlsp.rlspfoodapi.domain.model;

import lombok.Getter;

@Getter
public enum StatusOrderEnum {

    CREATED("Order Created"),
    CONFIRMED("Order Confirmed"),
    STARTED("Order Started"),
    ON_THE_OVEN("Order on the oven"),
    READY("Order ready to delivery"),
    ON_THE_ROAD("Order on the road"),
    DELIVERED("Order Delivered"),
    CANCELED("Order Canceled");

    private String description;

    StatusOrderEnum(String description) {
        this.description = description;
    }
}
