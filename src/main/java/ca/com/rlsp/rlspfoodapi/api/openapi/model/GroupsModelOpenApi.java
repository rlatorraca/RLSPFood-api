package ca.com.rlsp.rlspfoodapi.api.openapi.model;

import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CuisineOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.GroupOutputDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.util.List;

@ApiModel("GroupsModel")
@Getter
@Setter
public class GroupsModelOpenApi {

    private GroupsEmbeddedModelOpenApi _embedded;
    private Link _links;


    @ApiModel("GroupsEmbeddedModelOpenApi")
    @Data
    public class GroupsEmbeddedModelOpenApi  {
        private List<GroupOutputDto> groups;

    }
}
