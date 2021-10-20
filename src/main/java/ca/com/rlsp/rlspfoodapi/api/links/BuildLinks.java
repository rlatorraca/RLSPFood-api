package ca.com.rlsp.rlspfoodapi.api.links;

import ca.com.rlsp.rlspfoodapi.api.controller.*;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BuildLinks {

    public static final TemplateVariables VARIABLES_PAGINATION =  new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)

    );

    public static final TemplateVariables VARIABLES_FILTER = new TemplateVariables(
            new TemplateVariable("userId", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("startDate", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("endDate", TemplateVariable.VariableType.REQUEST_PARAM)

    );

    public Link getLinkToOrders() {
        String orderURI = linkTo(methodOn(OrderController.class).searchByFilterPageable(null,
                null)).toUri().toString();

        return Link.of(UriTemplate.of(orderURI, VARIABLES_PAGINATION.concat(VARIABLES_FILTER)),"order-short");
    }

    public Link getLinkToRestaurant(Long restaurantId, String relation) {
        return linkTo(methodOn(RestaurantController.class)
                .findById(restaurantId)).withRel(relation);
    }

    public Link getLinkToRestaurant(Long restaurantId) {
        return getLinkToRestaurant(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link getLinkToUser(Long orderId,  String relation) {
       return linkTo(methodOn(UserController.class)
                .findById(orderId)).withRel(relation);
    }

    public Link getLinkToUser(Long orderId) {
        return getLinkToUser(orderId, IanaLinkRelations.SELF.value());
    }

    public Link getLinkToUsers(String relation) {
        return getLinkToUsers(IanaLinkRelations.SELF.value());
    }

    public Link getLinkToPaymentType(Long orderId,  String relation) {
        return linkTo(methodOn(PaymentTypeController.class)
                .findById(orderId, null)).withRel(relation);
    }

    public Link getLinkToPaymentType(Long paymentTypeId) {
        return getLinkToPaymentType(paymentTypeId, IanaLinkRelations.SELF.value());
    }

    public Link getLinkToAddressDelivery(Long cityId,  String relation) {
        return linkTo(methodOn(CityController.class)
                .findById(cityId)).withRel(relation);
    }

    public Link getLinkToAddressDelivery(Long cityId) {
        return getLinkToAddressDelivery(cityId, IanaLinkRelations.SELF.value());
    }

    public Link getLinkToOrderItems(Long restaurantId,  Long productId, String relation) {

        return linkTo(methodOn(RestaurantProductController.class)
                .findByRestaurantIdAndByProductId(restaurantId , productId))
                .withRel(relation);
    }

    public Link getLinkToOrderItems(Long restaurantId,  Long productId) {
        return getLinkToOrderItems(restaurantId, productId, IanaLinkRelations.SELF.value());
    }

    public Link getLinkToUserGroups(Long userId, String rel) {
        return linkTo(methodOn(UserGroupController.class)
                .listAll(userId)).withRel(rel);
    }

    public Link getLinkToUserGroups(Long userId) {
        return getLinkToUserGroups(userId, IanaLinkRelations.SELF.value());
    }

    public Link getLinkRestaurantManagers(Long restaurantId, String relation) {
        return linkTo(methodOn(RestaurantUserManagerController.class)
                .listOne(restaurantId)).withRel(relation);
    }

    public Link getLinkRestaurantManagers(Long restaurantId) {
        return getLinkRestaurantManagers(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link getLinkPaymentType(Long paymentTypeId, String relation) {
        return linkTo(methodOn(PaymentTypeController.class)
                .findById(paymentTypeId, null)).withRel(relation);
    }

    public Link getLinkPaymentType(Long paymentTypeId) {
        return getLinkPaymentType(paymentTypeId, IanaLinkRelations.SELF.value());
    }

    public Link getLinkToCities(Long cityId, String relation) {
        return linkTo(methodOn(CityController.class)
                .findById(cityId)).withRel(relation);
    }

    public Link getLinkToCities(Long cityId) {
        return getLinkToCities(cityId, IanaLinkRelations.SELF.value());
    }

    public Link getLinkToCities(String relation) {
        return linkTo(CityController.class).withRel(relation);
    }

    public Link getLinkToCities() {
        return getLinkToCities(IanaLinkRelations.SELF.value());
    }

    public Link getLinkToProvince(Long provinceId, String relation) {
        return linkTo(methodOn(ProvinceController.class)
                .findById(provinceId)).withRel(relation);
    }

    public Link getLinkToProvince(Long provinceId) {
        return getLinkToProvince(provinceId, IanaLinkRelations.SELF.value());
    }

    public Link getLinkToProvinces(String relation) {
        return linkTo(ProvinceController.class).withRel(relation);
    }

    public Link getLinkToProvinces() {
        return getLinkToProvinces(IanaLinkRelations.SELF.value());
    }

    public Link getLinkToCuisines(String rel) {
        return linkTo(CuisineController.class).withRel(rel);
    }

    public Link getLinkToCuisines() {
        return getLinkToCuisines(IanaLinkRelations.SELF.value());
    }

    public Link getLinkToCreateAnOrder(String orderCode, String relation) {
        return linkTo(methodOn(StatusOrderController.class).create(orderCode)).withRel(relation);
    }
    public Link getLinkToConfirmAnOrder(String orderCode, String relation) {
        return linkTo(methodOn(StatusOrderController.class).confirm(orderCode)).withRel(relation);
    }

    public Link getLinkToStartAnOrder(String orderCode, String relation) {
        return linkTo(methodOn(StatusOrderController.class).start(orderCode)).withRel(relation);
    }

    public Link getLinkToCancelAnOrder(String orderCode, String relation) {
        return linkTo(methodOn(StatusOrderController.class).cancel(orderCode)).withRel(relation);
    }

    public Link getLinkToOvenAnOrder(String orderCode, String relation) {
        return linkTo(methodOn(StatusOrderController.class).oven(orderCode)).withRel(relation);
    }

    public Link getLinkToRoadAnOrder(String orderCode, String relation) {
        return linkTo(methodOn(StatusOrderController.class).road(orderCode)).withRel(relation);
    }

    public Link getLinkToReadyAnOrder(String orderCode, String relation) {
        return linkTo(methodOn(StatusOrderController.class).ready(orderCode)).withRel(relation);
    }

    public Link getLinkToDeliveryAnOrder(String orderCode, String relation) {
        return linkTo(methodOn(StatusOrderController.class).deliver(orderCode)).withRel(relation);
    }


}
