package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.OrderInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.OrderOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderModelAssembler {

    @Autowired
    private ModelMapper modelMapper;


    /*
        Convert MODEL -> DTO para PUT
    */
    public OrderOutputDto fromControllerToOutput(Order order) {

        return modelMapper.map(order, OrderOutputDto.class); // Mas o mapeamento substituindo o codigo acima
    }



    /*
        Convert MODEL -> DTO (list GET)
    */
    public List<OrderOutputDto> fromControllerToOutputList(Collection<Order> orders){
        return orders.stream()
                .map(order -> fromControllerToOutput(order))
                .collect(Collectors.toList());
    }
}
