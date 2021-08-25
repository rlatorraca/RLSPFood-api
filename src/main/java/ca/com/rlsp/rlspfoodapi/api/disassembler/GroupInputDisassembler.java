package ca.com.rlsp.rlspfoodapi.api.disassembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.GroupInputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Group;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Group fromInputToController(GroupInputDto groupInputDto) {
        return modelMapper.map(groupInputDto, Group.class);
    }
    
    public void fromDTOtoGroup(GroupInputDto groupInputDto, Group group) {

        modelMapper.map(groupInputDto, group);
    }   
} 