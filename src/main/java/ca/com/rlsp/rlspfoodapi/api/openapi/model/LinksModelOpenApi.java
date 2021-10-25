package ca.com.rlsp.rlspfoodapi.api.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Links")
public class LinksModelOpenApi {

    private LinkModel relation;

    @Setter
    public class LinkModel {

        private String href;
        private boolean templated;
    }
}
