package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.controller.PermissionController;
import ca.com.rlsp.rlspfoodapi.api.links.BuildLinks;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.PermissionInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PermissionOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionModelAssembler extends RepresentationModelAssemblerSupport<Permission, PermissionOutputDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildLinks buildLinks;

    public PermissionModelAssembler() {
        super(PermissionController.class, PermissionOutputDto.class);
    }


    /*
        Convert MODEL -> DTO para PUT
    */
    public PermissionOutputDto fromControllerToOutput(Permission permission) {

        return modelMapper.map(permission, PermissionOutputDto.class); // Mas o mapeamento substituindo o codigo acima
    }

    /*
      Convert MODEL -> DTO
  */
    public PermissionInputDto fromControllerToInput(Permission permission) {
        PermissionInputDto permissionInputDto = new PermissionInputDto();

        permissionInputDto.setId(permission.getId());
        permissionInputDto.setName(permission.getName());
        permissionInputDto.setDescription(permission.getDescription());

        return permissionInputDto;
    }

    /*
        Convert MODEL -> DTO (list GET)
    */
    public List<PermissionOutputDto> fromControllerToOutputList(Collection<Permission> permissions){
        return permissions.stream()
                .map(permission -> fromControllerToOutput(permission))
                .collect(Collectors.toList());
    }

    @Override
    public PermissionOutputDto toModel(Permission permission) {
        PermissionOutputDto permissionOutputDto = createModelWithId(permission.getId(), permission);
        modelMapper.map(permission, permissionOutputDto);

        return permissionOutputDto;
    }

    @Override
    public CollectionModel<PermissionOutputDto> toCollectionModel(Iterable<? extends Permission> permissions) {
        return super.toCollectionModel(permissions)
                .add(buildLinks.getLinkToPermissions());
    }

}
