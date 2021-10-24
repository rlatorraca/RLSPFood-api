package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.controller.GroupController;
import ca.com.rlsp.rlspfoodapi.api.links.BuildLinks;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.GroupInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.GroupOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupModelAssembler extends RepresentationModelAssemblerSupport<Group, GroupOutputDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildLinks buildLinks;


    public GroupModelAssembler() {
        super(GroupController.class, GroupOutputDto.class);
    }


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
    public List<GroupOutputDto> fromControllerToOutputList(Collection<Group> groups){
        return groups.stream()
                .map(group -> fromControllerToOutput(group))
                .collect(Collectors.toList());
    }

    @Override
    public GroupOutputDto toModel(Group group) {

        GroupOutputDto groupOutputDto = createModelWithId(group.getId(), group);
        modelMapper.map(group, groupOutputDto);

        groupOutputDto.add(buildLinks.getLinkToGroups("groups"));
        groupOutputDto.add(buildLinks.getLinkToGroupPermissions(group.getId(), "permissions"));

        return groupOutputDto;
    }

    @Override
    public CollectionModel<GroupOutputDto> toCollectionModel(Iterable<? extends Group> groups) {
        return super.toCollectionModel(groups)
                .add(buildLinks.getLinkToGroups());
    }
}
