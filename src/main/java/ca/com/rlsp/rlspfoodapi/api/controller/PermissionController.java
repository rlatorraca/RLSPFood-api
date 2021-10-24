package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.PermissionModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PermissionOutputDto;
import ca.com.rlsp.rlspfoodapi.api.openapi.controller.PermissionControllerOpenApi;
import ca.com.rlsp.rlspfoodapi.domain.model.Permission;
import ca.com.rlsp.rlspfoodapi.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController implements PermissionControllerOpenApi {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionModelAssembler permissionModelAssembler;


    @GetMapping
    public CollectionModel<PermissionOutputDto> listAll() {
        List<Permission> allPermissions = permissionRepository.findAll();

        return permissionModelAssembler.toCollectionModel(allPermissions);

    }
}
