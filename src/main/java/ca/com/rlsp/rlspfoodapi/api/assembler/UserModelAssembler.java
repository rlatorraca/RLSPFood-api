package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.controller.CityController;
import ca.com.rlsp.rlspfoodapi.api.controller.UserController;
import ca.com.rlsp.rlspfoodapi.api.controller.UserGroupController;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.UserOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserOutputDto> {

    @Autowired
    private ModelMapper modelMapper;

    public UserModelAssembler() {
        super(UserController.class, UserOutputDto.class);
    }

    public UserOutputDto fromControllerToOutput(User user) {
        UserOutputDto userOutputDto = createModelWithId(user.getId(), user);
        modelMapper.map(user, userOutputDto);
        return modelMapper.map(user, UserOutputDto.class);
    }

    public List<UserOutputDto> fromControllerToOutputList(Collection<User> users) {
        return users.stream()
                .map(client -> fromControllerToOutput(client))
                .collect(Collectors.toList());
    }

    @Override
    public UserOutputDto toModel(User user) {
        UserOutputDto userOutputDto = createModelWithId(user.getId(), user);
        modelMapper.map(user, userOutputDto);

        userOutputDto.add(
                linkTo(methodOn(UserController.class)
                        .listaAll())
                        .withRel("users")
        );

        userOutputDto.add(
                linkTo(methodOn(UserGroupController.class)
                        .listAll(user.getId()))
                        .withRel("user-groups")
        );

        return userOutputDto;
    }

    @Override
    public CollectionModel<UserOutputDto> toCollectionModel(Iterable<? extends User> users) {
        return super.toCollectionModel(users)
                .add(linkTo(CityController.class)
                        .withSelfRel()
                );
    }
}
