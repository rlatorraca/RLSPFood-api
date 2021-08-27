package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.ClientModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.ClientInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ClientAndPasswordInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ClientInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.PasswordInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ClientOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.exception.ProvinceNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.model.Client;
import ca.com.rlsp.rlspfoodapi.domain.repository.ClientRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.ClientRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientRegistrationService clientRegistrationService;

    @Autowired
    private ClientModelAssembler clientModelAssembler;

    @Autowired
    private ClientInputDisassembler clientInputDisassembler;

    @GetMapping
    public List<ClientOutputDto> listaAll() {
        List<Client> todasUsuarios = clientRepository.findAll();

        return clientModelAssembler.fromControllerToOutputList(todasUsuarios);
    }

    @GetMapping("/{clientId}")
    public ClientOutputDto findById(@PathVariable("clientId") Long id) {
        Client client = clientRegistrationService.findOrFail(id);

        return clientModelAssembler.fromControllerToOutput(client);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientOutputDto save(@RequestBody @Valid ClientAndPasswordInputDto clientAndPasswordInputDto) {
        try{
            Client client = clientInputDisassembler.fromInputToController(clientAndPasswordInputDto);
            client = clientRegistrationService.save(client);

            return clientModelAssembler.fromControllerToOutput(client);
        } catch (EntityNotFoundException e){
            throw new GenericBusinessException(e.getReason(), e);
        }

    }

    @PutMapping("/{clientId}")
    public ClientOutputDto update(@PathVariable("clientId") Long id,
                                  @RequestBody @Valid ClientInputDto clientInputDto) {

        try{
            Client currentClient = clientRegistrationService.findOrFail(id);
            clientInputDisassembler.fromDTOtoClient(clientInputDto, currentClient);
            currentClient = clientRegistrationService.save(currentClient);
            return clientModelAssembler.fromControllerToOutput(currentClient);
        } catch (ProvinceNotFoundException e ) {
            throw new GenericBusinessException(e.getReason(), e);
        }


    }

    @PutMapping("/{clientId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable Long usuarioId, @RequestBody @Valid PasswordInputDto passwordInputDto) {
        clientRegistrationService.changePassword(usuarioId, passwordInputDto.getCurrantPassword(), passwordInputDto.getNewPassword());
    }
}
