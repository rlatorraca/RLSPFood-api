package ca.com.rlsp.rlspfoodapi.api.model.dto.output;

import ca.com.rlsp.rlspfoodapi.domain.model.Address;
import ca.com.rlsp.rlspfoodapi.domain.model.PaymentType;
import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RestaurantOutputDTO {

    private Long id;

    private String name;

    private BigDecimal deliveryFee;

    private CuisineOutputDTO cuisine;

    private AddressOutputDTO address;

    private OffsetDateTime createdDate;

    private OffsetDateTime dateLastUpdate;

}
