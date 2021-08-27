package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ClientOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ClientOutputDto fromControllerToOutput(Client client) {
        return modelMapper.map(client, ClientOutputDto.class);
    }

    public List<ClientOutputDto> fromControllerToOutputList(List<Client> clients) {
        return clients.stream()
                .map(client -> fromControllerToOutput(client))
                .collect(Collectors.toList());
    }
}
