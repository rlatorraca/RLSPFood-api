package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.GroupInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.GroupOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GroupModelAssembler {

    @Autowired
    private ModelMapper modelMapper;


    /*
        Convert MODEL -> DTO para PUT
    */
    public GroupOutputDto fromControllerToOutput(Group group) {

        return modelMapper.map(group, GroupOutputDto.class); // Mas o mapeamento substituindo o codigo acima
    }


    /*
        Convert MODEL -> DTO
    */
    public GroupInputDto fromControllerToInput(Group group) {
        GroupInputDto groupInputDto = new GroupInputDto();

        groupInputDto.setName(group.getName());

        return groupInputDto;
    }


    /*
        Convert MODEL -> DTO (list GET)
    */
    public List<GroupOutputDto> fromControllerToOutputList(List<Group> groups){
        return groups.stream()
                .map(group -> fromControllerToOutput(group))
                .collect(Collectors.toList());
    }
}
