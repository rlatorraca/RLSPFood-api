package ca.com.rlsp.rlspfoodapi.api.disassembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProvinceInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Province fromInputToController(ProvinceInputDto provinceInputDTO) {
        return modelMapper.map(provinceInputDTO, Province.class);
    }
    
    public void fromDTOtoProvince(ProvinceInputDto provinceInputDTO, Province province) {
        modelMapper.map(provinceInputDTO, province);
    }   
} 