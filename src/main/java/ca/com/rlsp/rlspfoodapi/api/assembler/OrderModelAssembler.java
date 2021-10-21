package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.controller.*;
import ca.com.rlsp.rlspfoodapi.api.links.BuildLinks;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.OrderOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.OrderShortOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderOutputDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildLinks buildLinks;

    public OrderModelAssembler() {
        super(OrderController.class, OrderOutputDto.class);
    }


    /*
        Convert MODEL -> DTO para PUT
    */
    public OrderOutputDto fromControllerToOutput(Order order) {

        return modelMapper.map(order, OrderOutputDto.class); // Mas o mapeamento substituindo o codigo acima
    }

    public OrderShortOutputDto fromControllerToShortOutput(Order order) {

        return modelMapper.map(order, OrderShortOutputDto.class); // Mas o mapeamento substituindo o codigo acima
    }



    /*
        Convert MODEL -> DTO (list GET)
    */
    public List<OrderOutputDto> fromControllerToOutputList(Collection<Order> orders){
        return orders.stream()
                .map(order -> fromControllerToOutput(order))
                .collect(Collectors.toList());
    }

    @Override
    public OrderOutputDto toModel(Order order) {
        OrderOutputDto orderOutputDto = createModelWithId(order.getId(), order);
        modelMapper.map(order, orderOutputDto);

        //searchByFilterPageable
        orderOutputDto.add(buildLinks.getLinkToOrders());


        //orderOutputDto.add(linkTo(OrderController.class).withRel("orders"));

        orderOutputDto.getRestaurant().add(buildLinks
                .getLinkToRestaurant(order.getRestaurant().getId()));

        orderOutputDto.getUser().add(buildLinks
                .getLinkToUser(order.getUser().getId()));

        // Passamos null no segundo argumento, porque é indiferente para a
        // construção da URL do recurso de forma de pagamento
        orderOutputDto.getPaymentType().add(buildLinks
                .getLinkToPaymentType(order.getPaymentType().getId()));

        orderOutputDto.getAddressDelivery().getCity().add(buildLinks
                .getLinkToAddressDelivery(order.getAddressDelivery().getCity().getId()));


        orderOutputDto.getOrderItems().forEach(item -> {
            item.add(buildLinks
                    .getLinkToOrderItems(order.getRestaurant().getId(), item.getProductId(), "item"));
        });

        /* Links to Order Status*/
        if (order.canCreateOrder()) {
            orderOutputDto.add(buildLinks.getLinkToCreateAnOrder(order.getOrderCode(), "create order"));
        }

        if (order.canConfirmOrder()) {
            orderOutputDto.add(buildLinks.getLinkToConfirmAnOrder(order.getOrderCode(), "confirm order"));
        }

        if (order.canCancelOrder()) {
            orderOutputDto.add(buildLinks.getLinkToCancelAnOrder(order.getOrderCode(), "cancel order"));
        }

        if (order.canStartOrder()) {
            orderOutputDto.add(buildLinks.getLinkToStartAnOrder(order.getOrderCode(), "start order"));
        }

        if (order.canOnTheOvenOrder()) {
            orderOutputDto.add(buildLinks.getLinkToOvenAnOrder(order.getOrderCode(), "oven order"));
        }

        if (order.canReadyOrder()) {
            orderOutputDto.add(buildLinks.getLinkToReadyAnOrder(order.getOrderCode(), "ready order"));
        }

        if (order.canOnTheRoadOrder()) {
            orderOutputDto.add(buildLinks.getLinkToRoadAnOrder(order.getOrderCode(), "road order"));
        }

        if (order.canDeliveryOrder()) {
            orderOutputDto.add(buildLinks.getLinkToDeliveryAnOrder(order.getOrderCode(), "delivery order"));
        }

        return orderOutputDto;
    }



//    orderOutputDto.getRestaurant().add(linkTo(methodOn(RestaurantController.class)
//                .findById(order.getRestaurant().getId())).withSelfRel());
//
//        orderOutputDto.getUser().add(linkTo(methodOn(UserController.class)
//                .findById(order.getUser().getId())).withSelfRel());
//
//    // Passamos null no segundo argumento, porque é indiferente para a
//    // construção da URL do recurso de forma de pagamento
//        orderOutputDto.getPaymentType().add(linkTo(methodOn(PaymentTypeController.class)
//                .findById(order.getPaymentType().getId(), null)).withSelfRel());
//
//        orderOutputDto.getAddressDelivery().getCity().add(linkTo(methodOn(CityController.class)
//                .findById(order.getAddressDelivery().getCity().getId())).withSelfRel());
//
//        orderOutputDto.getOrderItems().forEach(item -> {
//        item.add(linkTo(methodOn(RestaurantProductController.class)
//                .buscar(order.getRestaurant().getId(), item.getProductId()))
//                .withRel("produto"));

}
