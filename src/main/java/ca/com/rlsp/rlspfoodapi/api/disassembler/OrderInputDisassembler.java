package ca.com.rlsp.rlspfoodapi.api.disassembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.OrderInputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Order fromInputToController(OrderInputDto orderInputDto) {
        return modelMapper.map(orderInputDto, Order.class);
    }
    
    public void fromDTOtoCity(OrderInputDto orderInputDto, Order order) {
        modelMapper.map(orderInputDto, order);
    }   
} 