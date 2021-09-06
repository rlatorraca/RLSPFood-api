package ca.com.rlsp.rlspfoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Column(name = "beforetax", nullable = false)
    private BigDecimal beforeTax;
    @Column(name = "deliveryfee", nullable = false)
    private BigDecimal deliveryFee;
    @Column(name = "aftertax", nullable = false)
    private BigDecimal afterTax;
    @Column(name = "taxpercentual", nullable = false)
    private BigDecimal taxes;

    @CreationTimestamp
    @Column(name = "createddate", nullable = false)
    private OffsetDateTime createdDate;
    @UpdateTimestamp
    @Column(name = "confirmationdate")
    private OffsetDateTime confirmationDate;
    @Column(name = "canceldate")
    private OffsetDateTime cancelDate;

    @Column(name = "deliverydate")
    private OffsetDateTime deliveryDate;

    //@Column(name = "order_address")
    @Embedded
    private Address addressDelivery;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusOrderEnum status = StatusOrderEnum.CREATED;

    @ManyToOne(fetch = FetchType.LAZY) // padrao : Eager
    @JoinColumn(name = "paymenttype_id", nullable = false)
    private PaymentType paymentType;

    @ManyToOne // padrao : Eager
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne // padrao : Eager
    @JoinColumn(name = "user_client_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // padrao : LAZY
    private List<OrderItem> orderItems = new ArrayList<>();

    // CALCULATE Total Value
    public void calculateTotalValue() {
        this.beforeTax = getOrderItems().stream()
                .map(item -> item.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal tax = BigDecimal.ONE.add(this.restaurant
                                                    .getAddress()
                                                    .getCity()
                                                    .getProvince()
                                                    .getTax()
                                                    .getTaxPercentual());
        this.afterTax = (this.beforeTax.multiply(tax)).add(this.deliveryFee);
    }

    public void setDelivery() {
        setDeliveryFee(getRestaurant().getDeliveryFee());
    }

    public void addItemsInOrder() {
        getOrderItems().forEach(item -> item.setOrder(this));
    }

}
