package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.PermissionModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PermissionOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Group;
import ca.com.rlsp.rlspfoodapi.domain.service.GroupRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/groups/{groupId}/permissions")
public class GroupPermissionController {

    private GroupRegistrationService groupRegistrationService;
    private PermissionModelAssembler permissionModelAssembler;

    public GroupPermissionController(GroupRegistrationService groupRegistrationService,
                                     PermissionModelAssembler permissionModelAssembler) {
        this.groupRegistrationService = groupRegistrationService;
        this.permissionModelAssembler = permissionModelAssembler;
    }

    @GetMapping
    public List<PermissionOutputDto> listar(@PathVariable Long groupId) {
        Group group = groupRegistrationService.findOrFail(groupId);

        return permissionModelAssembler.fromControllerToOutputList(group.getPermissions());
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupRegistrationService.detachPermission(groupId, permissionId);
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupRegistrationService.attachPermission(groupId, permissionId);
    }
}
