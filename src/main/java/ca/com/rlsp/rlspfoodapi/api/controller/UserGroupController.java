package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.GroupModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.GroupOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.User;
import ca.com.rlsp.rlspfoodapi.domain.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/users/{userId}/groups")
public class UserGroupController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private GroupModelAssembler groupModelAssembler;

    @GetMapping
    public List<GroupOutputDto> listAll(@PathVariable Long userId) {
        User user = userRegistrationService.findOrFail(userId);

        return groupModelAssembler.fromControllerToOutputList(user.getGroups());
    }
    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void detachGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        userRegistrationService.detachGroup(userId, groupId);
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void attachGroup(@PathVariable Long userId, @PathVariable Long groupId) {
        userRegistrationService.attachGroup(userId, groupId);
    }
}
