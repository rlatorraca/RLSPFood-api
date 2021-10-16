package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "user-collection")
@Getter
@Setter
public class UserOutputDto extends RepresentationModel<UserOutputDto> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Michael Minotauru")
    private String name;

    @ApiModelProperty(example = "minotauro@gmail.com")
    private String email;
}
