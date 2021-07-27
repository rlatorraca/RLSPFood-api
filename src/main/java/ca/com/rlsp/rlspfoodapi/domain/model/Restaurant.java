package ca.com.rlsp.rlspfoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbl_restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name="name_restaurant", length = 100, nullable = false)
    private String name;

    @Column(name="delivery_fee", nullable = false)
    private BigDecimal deliveryFee;

    @ManyToOne
    @JoinColumn(name = "cuisine_id", nullable = false)
    private Cuisine cuisine;

    @JsonIgnore
    @Embedded // Essa propriedade e do Tipo @Embadeble
    private Address address;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_date", nullable = false, columnDefinition = "datetime")
    private LocalDateTime createdDate;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "date_last_update",nullable = false, columnDefinition = "datetime")
    private LocalDateTime dateLastUpdate;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tbl_restaurant_paymenttype",
               joinColumns = @JoinColumn(name = "restaurant_id"),
               inverseJoinColumns = @JoinColumn(name = "payment_type_id"))
    private List<PaymentType> paymentTypeList = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

}
