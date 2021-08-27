package ca.com.rlsp.rlspfoodapi.api.disassembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ClientInputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Client;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Client fromInputToController(ClientInputDto clientInputDto) {
        return modelMapper.map(clientInputDto, Client.class);
    }
    
    public void fromDTOtoClient(ClientInputDto clientInputDto, Client client) {

        modelMapper.map(clientInputDto, client);
    }   
} 