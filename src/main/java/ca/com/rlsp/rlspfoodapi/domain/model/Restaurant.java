package ca.com.rlsp.rlspfoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode
@Data
@Entity
@Table(name = "tbl_restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name_restaurant")
    private String name;

    @Column(name="delivery_fee")
    private BigDecimal deliveryFee;

}
