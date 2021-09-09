package ca.com.rlsp.rlspfoodapi.domain.model;

import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
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
    @Column(name = "confirmationdate")
    private OffsetDateTime confirmationDate;
    @Column(name = "starteddate")
    private OffsetDateTime startedDate;
    @Column(name = "ontheovendate")
    private OffsetDateTime onTheOvenDate;
    @Column(name = "readydate")
    private OffsetDateTime readyDate;
    @Column(name = "ontheroad")
    private OffsetDateTime onTheRoadDate;
    @Column(name = "canceleddate")
    private OffsetDateTime canceledDate;

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
        getOrderItems().forEach(OrderItem::calculateTotalPriceForItems);

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

    public void confirm() {
        setStatus(StatusOrderEnum.CONFIRMED);
        setConfirmationDate(OffsetDateTime.now());
    }

    public void start() {
        setStatus(StatusOrderEnum.STARTED);
        setStartedDate(OffsetDateTime.now());
    }

    public void onTheOven() {
        setStatus(StatusOrderEnum.ON_THE_OVEN);
        setOnTheOvenDate(OffsetDateTime.now());
    }

    public void ready() {
        setStatus(StatusOrderEnum.READY);
        setReadyDate(OffsetDateTime.now());
    }


    public void ontTheRoad() {
        setStatus(StatusOrderEnum.ON_THE_ROAD);
        setOnTheRoadDate(OffsetDateTime.now());
    }

    public void delivered() {
        setStatus(StatusOrderEnum.DELIVERED);
        setDeliveryDate(OffsetDateTime.now());
    }

    public void cancel() {
        setStatus(StatusOrderEnum.CANCELED);
        setCanceledDate(OffsetDateTime.now());
    }

    public static final String MSG_STATUS_ORDER_CANNOT_BE_CHANGED="Order Status %d cannot be changed from %s to %s";

    private void setStatus(StatusOrderEnum newStatus) {
        if(!getStatus().authorizedModifyStatusTo(newStatus)) {
            throw new GenericBusinessException(
                    String.format(
                            MSG_STATUS_ORDER_CANNOT_BE_CHANGED,
                            this.getId(),
                            this.getStatus().getDescription(),
                            newStatus.getDescription()));
        }
        this.status = newStatus;
    }

}
