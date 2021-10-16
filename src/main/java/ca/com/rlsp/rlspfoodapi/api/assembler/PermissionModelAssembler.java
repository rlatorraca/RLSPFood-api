package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.PermissionInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PermissionOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Permission;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionModelAssembler {

    @Autowired
    private ModelMapper modelMapper;


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
}
