package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.controller.OrderController;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.OrderInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.OrderOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.OrderShortOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<Order, OrderOutputDto> {

    @Autowired
    private ModelMapper modelMapper;

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

        orderOutputDto.add(linkTo(OrderController.class).withRel("orders"));


        return orderOutputDto;


    }

}
