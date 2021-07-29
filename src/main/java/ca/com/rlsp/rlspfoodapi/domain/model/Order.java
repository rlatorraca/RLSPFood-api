package ca.com.rlsp.rlspfoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "order_beforeTax")
    private BigDecimal beforeTax;
    @Column(name = "order_deliveryFee")
    private BigDecimal deliveryFee;
    @Column(name = "order_afterTax")
    private BigDecimal afterTax;

    @CreationTimestamp
    @Column(name = "order_createdDate")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "order_modifiedDate")
    private LocalDateTime modifiedDate;

    @Column(name = "order_cancelDate")
    private LocalDateTime cancelDate;
    @Column(name = "order_deliveryDate")
    private LocalDateTime deliveryDate;

    @Column(name = "order_status")
    private  StatusOrder status;

    @Column(name = "order_addressDelivery")
    @Embedded
    private Address addressDelivery;

    @Column(name = "order_paymenttype_id")
    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentType paymentType;




}
