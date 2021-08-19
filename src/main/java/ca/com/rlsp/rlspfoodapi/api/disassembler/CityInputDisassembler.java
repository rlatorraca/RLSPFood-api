package ca.com.rlsp.rlspfoodapi.api.disassembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDTO;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public City fromInputToController(CityInputDTO cityInputDTO) {
        return modelMapper.map(cityInputDTO, City.class);
    }
    
    public void fromDTOtoCity(CityInputDTO cityInputDTO, City city) {
        city.setProvince(new Province());
        modelMapper.map(cityInputDTO, city);
    }   
} 