package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.output.UserOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UserOutputDto fromControllerToOutput(User user) {
        return modelMapper.map(user, UserOutputDto.class);
    }

    public List<UserOutputDto> fromControllerToOutputList(List<User> users) {
        return users.stream()
                .map(client -> fromControllerToOutput(client))
                .collect(Collectors.toList());
    }
}
