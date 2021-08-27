package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.GroupModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.GroupInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.GroupInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.GroupOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Group;
import ca.com.rlsp.rlspfoodapi.domain.repository.GroupRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.GroupRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/groups")
public class GroupController {

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupOutputDto save(@PathVariable @Valid GroupInputDto groupInput){
        Group group = groupInputDisassembler.fromInputToController(groupInput);
        group =  groupRegistrationService.save(group);
        return groupModelAssembler.fromControllerToOutput(group);
    }

    @PutMapping("{groupId}")
    public GroupOutputDto update(@PathVariable("groupId") Long id,
                                 @RequestBody @Valid GroupInputDto groupInputDto){
        Group currentGroup = groupRegistrationService.FindAndFail(id);
        groupInputDisassembler.fromDTOtoGroup(groupInputDto, currentGroup);
        currentGroup = groupRegistrationService.save(currentGroup);
        return groupModelAssembler.fromControllerToOutput(currentGroup);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("groupId") Long id) {
        groupRegistrationService.delete(id);
    }
}
