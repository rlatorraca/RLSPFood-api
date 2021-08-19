package ca.com.rlsp.rlspfoodapi.api.disassembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CuisineInputDTO;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CuisineInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Cuisine fromInputToController(CuisineInputDTO cuisineInputDTO) {
        return modelMapper.map(cuisineInputDTO, Cuisine.class);
    }
    
    public void fromDTOtoCuisine(CuisineInputDTO cuisineInputDTO, Cuisine cuisine) {
        modelMapper.map(cuisineInputDTO, cuisine);
    }   
} 