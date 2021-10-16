package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.UserModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.UserInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.UserAndPasswordInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.UserInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.PasswordInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.UserOutputDto;
import ca.com.rlsp.rlspfoodapi.api.openapi.controller.UserControllerOpenApi;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.exception.ProvinceNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.model.User;
import ca.com.rlsp.rlspfoodapi.domain.repository.UserRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.UserRegistrationService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController implements UserControllerOpenApi {

    private UserRepository userRepository;
    private UserRegistrationService userRegistrationService;
    private UserModelAssembler userModelAssembler;
    private UserInputDisassembler userInputDisassembler;

    public UserController(UserRepository userRepository,
                          UserRegistrationService userRegistrationService,
                          UserModelAssembler userModelAssembler,
                          UserInputDisassembler userInputDisassembler) {

        this.userRepository = userRepository;
        this.userRegistrationService = userRegistrationService;
        this.userModelAssembler = userModelAssembler;
        this.userInputDisassembler = userInputDisassembler;
    }

    @GetMapping
    public CollectionModel<UserOutputDto> listaAll() {
        List<User> allUsers = userRepository.findAll();

        return userModelAssembler.toCollectionModel(allUsers);
    }

    /*
    @GetMapping
    public List<UserOutputDto> listaAll() {
        List<User> allUsers = userRepository.findAll();

        return userModelAssembler.fromControllerToOutputList(allUsers);
    }
     */

    @GetMapping("/{userId}")
    public UserOutputDto findById(@PathVariable("userId") Long id) {
        User user = userRegistrationService.findOrFail(id);

        return userModelAssembler.fromControllerToOutput(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOutputDto save(@RequestBody @Valid UserAndPasswordInputDto clientAndPasswordInputDto) {
        try{
            User user = userInputDisassembler.fromInputToController(clientAndPasswordInputDto);
            user = userRegistrationService.save(user);

            return userModelAssembler.fromControllerToOutput(user);
        } catch (EntityNotFoundException e){
            throw new GenericBusinessException(e.getReason(), e);
        }

    }

    @PutMapping("/{userId}")
    public UserOutputDto update(@PathVariable("userId") Long id,
                                @RequestBody @Valid UserInputDto userInputDto) {

        try{
            User currentUser = userRegistrationService.findOrFail(id);
            userInputDisassembler.fromDTOtoClient(userInputDto, currentUser);
            currentUser = userRegistrationService.save(currentUser);
            return userModelAssembler.fromControllerToOutput(currentUser);
        } catch (ProvinceNotFoundException e ) {
            throw new GenericBusinessException(e.getReason(), e);
        }


    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable(value = "userId") Long userId, @RequestBody @Valid PasswordInputDto passwordInputDto) {
        userRegistrationService.changePassword(userId, passwordInputDto.getCurrantPassword(), passwordInputDto.getNewPassword());
    }
}
