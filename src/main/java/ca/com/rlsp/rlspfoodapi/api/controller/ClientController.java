package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.ClientModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.ClientInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.UserAndPasswordInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.UserInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.PasswordInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.UserOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.exception.ProvinceNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.model.User;
import ca.com.rlsp.rlspfoodapi.domain.repository.UserRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private UserRepository userRepository;
    private UserRegistrationService userRegistrationService;
    private ClientModelAssembler clientModelAssembler;
    private ClientInputDisassembler clientInputDisassembler;

    public ClientController(UserRepository userRepository,
                            UserRegistrationService userRegistrationService,
                            ClientModelAssembler clientModelAssembler,
                            ClientInputDisassembler clientInputDisassembler) {

        this.userRepository = userRepository;
        this.userRegistrationService = userRegistrationService;
        this.clientModelAssembler = clientModelAssembler;
        this.clientInputDisassembler = clientInputDisassembler;
    }

    @GetMapping
    public List<UserOutputDto> listaAll() {
        List<User> todasUsuarios = userRepository.findAll();

        return clientModelAssembler.fromControllerToOutputList(todasUsuarios);
    }

    @GetMapping("/{clientId}")
    public UserOutputDto findById(@PathVariable("clientId") Long id) {
        User user = userRegistrationService.findOrFail(id);

        return clientModelAssembler.fromControllerToOutput(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOutputDto save(@RequestBody @Valid UserAndPasswordInputDto clientAndPasswordInputDto) {
        try{
            User user = clientInputDisassembler.fromInputToController(clientAndPasswordInputDto);
            user = userRegistrationService.save(user);

            return clientModelAssembler.fromControllerToOutput(user);
        } catch (EntityNotFoundException e){
            throw new GenericBusinessException(e.getReason(), e);
        }

    }

    @PutMapping("/{clientId}")
    public UserOutputDto update(@PathVariable("clientId") Long id,
                                @RequestBody @Valid UserInputDto userInputDto) {

        try{
            User currentUser = userRegistrationService.findOrFail(id);
            clientInputDisassembler.fromDTOtoClient(userInputDto, currentUser);
            currentUser = userRegistrationService.save(currentUser);
            return clientModelAssembler.fromControllerToOutput(currentUser);
        } catch (ProvinceNotFoundException e ) {
            throw new GenericBusinessException(e.getReason(), e);
        }


    }

    @PutMapping("/{clientId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable Long usuarioId, @RequestBody @Valid PasswordInputDto passwordInputDto) {
        userRegistrationService.changePassword(usuarioId, passwordInputDto.getCurrantPassword(), passwordInputDto.getNewPassword());
    }
}
