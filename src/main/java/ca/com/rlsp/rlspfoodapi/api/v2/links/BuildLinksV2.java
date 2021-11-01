package ca.com.rlsp.rlspfoodapi.api.v2.links;

import ca.com.rlsp.rlspfoodapi.api.v1.controller.*;
import ca.com.rlsp.rlspfoodapi.api.v2.controller.CityControllerV2;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BuildLinksV2 {


    public Link getLinkToCities(Long cityId, String rel) {
        return linkTo(methodOn(CityControllerV2.class)
                .findById(cityId)).withRel(rel);
    }

    public Link getLinkToCities(Long cityId) {
        return getLinkToCities(cityId, IanaLinkRelations.SELF.value());
    }

    public Link getLinkToCities(String rel) {
        return linkTo(CityControllerV2.class).withRel(rel);
    }

    public Link getLinkToCities() {
        return getLinkToCities(IanaLinkRelations.SELF.value());
    }





}
