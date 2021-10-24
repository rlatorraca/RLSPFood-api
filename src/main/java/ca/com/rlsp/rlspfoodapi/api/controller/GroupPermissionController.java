package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.PermissionModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.links.BuildLinks;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PermissionOutputDto;
import ca.com.rlsp.rlspfoodapi.api.openapi.controller.GroupPermissionControllerOpenApi;
import ca.com.rlsp.rlspfoodapi.domain.model.Group;
import ca.com.rlsp.rlspfoodapi.domain.service.GroupRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/groups/{groupId}/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupPermissionController implements GroupPermissionControllerOpenApi {

    private GroupRegistrationService groupRegistrationService;
    private PermissionModelAssembler permissionModelAssembler;
    private BuildLinks buildLinks;

    public GroupPermissionController(GroupRegistrationService groupRegistrationService,
                                     PermissionModelAssembler permissionModelAssembler,
                                     BuildLinks buildLinks) {
        this.groupRegistrationService = groupRegistrationService;
        this.permissionModelAssembler = permissionModelAssembler;
        this.buildLinks = buildLinks;
    }

    @Override
    @GetMapping
    public CollectionModel<PermissionOutputDto> listAll(@PathVariable Long groupId) {
        Group group = groupRegistrationService.findOrFail(groupId);

        CollectionModel<PermissionOutputDto> permissionOutputDto
                = permissionModelAssembler.toCollectionModel(group.getPermissions())
                .removeLinks()
                .add(buildLinks.getLinkToGroupPermissions(groupId))
                .add(buildLinks.getLinkToPermissionsAttach(groupId, "attach"));

        permissionOutputDto.getContent().forEach(permissionModel -> {
            permissionModel.add(buildLinks.getLinkToPermissionsDetach(
                    groupId, Long.parseLong(permissionModel.getId()), "detach"));
        });

        return permissionOutputDto;
    }

    /* OLD Implementation
    @Override
    @GetMapping
    public List<PermissionOutputDto> listAll(@PathVariable Long groupId) {
        Group group = groupRegistrationService.findOrFail(groupId);

        return permissionModelAssembler.fromControllerToOutputList(group.getPermissions());
    }
    */


    @Override
    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> detach(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupRegistrationService.detachPermission(groupId, permissionId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> attach(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupRegistrationService.attachPermission(groupId, permissionId);

        return ResponseEntity.noContent().build();
    }
}
