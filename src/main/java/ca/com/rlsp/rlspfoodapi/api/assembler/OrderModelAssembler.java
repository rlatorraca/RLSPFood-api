package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.controller.*;
import ca.com.rlsp.rlspfoodapi.api.links.BuildLinks;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.OrderOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.OrderShortOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        orderOutputDto.add(buildLinks.linkToOrders());


        //orderOutputDto.add(linkTo(OrderController.class).withRel("orders"));

        orderOutputDto.getRestaurant().add(linkTo(methodOn(RestaurantController.class)
                .findById(order.getRestaurant().getId())).withSelfRel());

        orderOutputDto.getUser().add(linkTo(methodOn(UserController.class)
                .findById(order.getUser().getId())).withSelfRel());

        // Passamos null no segundo argumento, porque é indiferente para a
        // construção da URL do recurso de forma de pagamento
        orderOutputDto.getPaymentType().add(linkTo(methodOn(PaymentTypeController.class)
                .findById(order.getPaymentType().getId(), null)).withSelfRel());

        orderOutputDto.getAddressDelivery().getCity().add(linkTo(methodOn(CityController.class)
                .findById(order.getAddressDelivery().getCity().getId())).withSelfRel());

        orderOutputDto.getOrderItems().forEach(item -> {
            item.add(linkTo(methodOn(RestaurantProductController.class)
                    .buscar(order.getRestaurant().getId(), item.getProductId()))
                    .withRel("produto"));
        });

        return orderOutputDto;


    }

}
