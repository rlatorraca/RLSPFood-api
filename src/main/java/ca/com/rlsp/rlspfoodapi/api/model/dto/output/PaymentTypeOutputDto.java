package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;

@Relation(collectionRelation = "paymentType")
@Getter
@Setter
public class PaymentTypeOutputDto extends RepresentationModel<PaymentTypeOutputDto> {

    private Long id;
    private String name;
}
