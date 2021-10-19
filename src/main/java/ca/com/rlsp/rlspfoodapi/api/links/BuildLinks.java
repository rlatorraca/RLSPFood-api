package ca.com.rlsp.rlspfoodapi.api.links;

import ca.com.rlsp.rlspfoodapi.api.controller.OrderController;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BuildLinks {

    public static final TemplateVariables VARIABLES_PAGINATION =  new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)

    );

    public static final TemplateVariables VARIABLES_FILTER = new TemplateVariables(
            new TemplateVariable("userId", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("startDate", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("endDate", TemplateVariable.VariableType.REQUEST_PARAM)

    );

    public Link linkToOrders() {
        String orderURI = linkTo(methodOn(OrderController.class).searchByFilterPageable(null,
                null)).toUri().toString();

        return Link.of(UriTemplate.of(orderURI, VARIABLES_PAGINATION.concat(VARIABLES_FILTER)),"order-short");
    }
}
