package ca.com.rlsp.rlspfoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "order_beforeTax", nullable = false)
    private BigDecimal beforeTax;
    @Column(name = "order_deliveryFee", nullable = false)
    private BigDecimal deliveryFee;
    @Column(name = "order_afterTax", nullable = false)
    private BigDecimal afterTax;

    @CreationTimestamp
    @Column(name = "order_createdDate", nullable = false)
    private OffsetDateTime createdDate;
    @UpdateTimestamp
    @Column(name = "order_modifiedDate")
    private OffsetDateTime modifiedDate;
    @Column(name = "order_cancelDate")
    private OffsetDateTime cancelDate;

    @Column(name = "order_deliveryDate")
    private OffsetDateTime deliveryDate;

    @Column(name = "order_addressDelivery")
    @Embedded
    private Address addressDelivery;

    @Column(name = "order_status")
    private StatusOrderEnum status;

    @ManyToOne
    @JoinColumn(name = "order_paymenttype_id", nullable = false)
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();


}
