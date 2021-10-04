package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.GroupModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.openapi.controller.GroupControllerOpenApi;
import ca.com.rlsp.rlspfoodapi.api.disassembler.GroupInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.GroupInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.GroupOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Group;
import ca.com.rlsp.rlspfoodapi.domain.repository.GroupRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.GroupRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController implements GroupControllerOpenApi {

    private GroupRepository groupRepository;
    private GroupModelAssembler groupModelAssembler;
    private GroupRegistrationService groupRegistrationService;
    private GroupInputDisassembler groupInputDisassembler;


    public GroupController(GroupRepository groupRepository,
                           GroupModelAssembler groupModelAssembler,
                           GroupRegistrationService groupRegistrationService,
                           GroupInputDisassembler groupInputDisassembler) {
        this.groupRepository = groupRepository;
        this.groupModelAssembler = groupModelAssembler;
        this.groupRegistrationService = groupRegistrationService;
        this.groupInputDisassembler = groupInputDisassembler;
    }

    @GetMapping
    public List<GroupOutputDto> listAll() {
        List<Group> todosGrupos = groupRepository.findAll();

        return groupModelAssembler.fromControllerToOutputList(todosGrupos);
    }

    @GetMapping("/{groupId}")
    //public City findById(@PathVariable Long cityId) {
    public GroupOutputDto findById(@PathVariable("groupId") Long id) {
        Group group = groupRegistrationService.findOrFail(id);


        //  return cityRegistrationService.findOrFail(cityId);

        return groupModelAssembler.fromControllerToOutput(group);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupOutputDto save(@RequestBody @Valid GroupInputDto groupInput){
        Group group = groupInputDisassembler.fromInputToController(groupInput);
        group =  groupRegistrationService.save(group);
        return groupModelAssembler.fromControllerToOutput(group);
    }

    @PutMapping("{groupId}")
    public GroupOutputDto update(@PathVariable("groupId") Long id,
                                 @RequestBody @Valid GroupInputDto groupInputDto){
        Group currentGroup = groupRegistrationService.findOrFail(id);
        groupInputDisassembler.fromDTOtoGroup(groupInputDto, currentGroup);
        currentGroup = groupRegistrationService.save(currentGroup);
        return groupModelAssembler.fromControllerToOutput(currentGroup);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("grupoId") Long id) {
        groupRegistrationService.delete(id);
    }
}
