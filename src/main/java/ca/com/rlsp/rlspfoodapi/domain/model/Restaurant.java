package ca.com.rlsp.rlspfoodapi.domain.model;

import ca.com.rlsp.rlspfoodapi.core.validation.DeliveryFeeAnnotation;
import ca.com.rlsp.rlspfoodapi.core.validation.GroupsBeanValidation;
import ca.com.rlsp.rlspfoodapi.core.validation.MulitpleDeliveryFeeAnnotation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
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

    //@NotNull // => NAO NULO
    // @NotEmpty // => NAO NULO e NAO VAZIO
    //@NotBlank // => NAO NULO,  NAO VAZIO e NAO EM  BRANCO
    //@NotBlank(groups = {GroupsBeanValidation.CuisineIdValidation.class})
    @NotBlank
    @Column(name="name_restaurant", length = 100, nullable = false)
    private String name;


    //@DecimalMin("1,99")
    //@PositiveOrZero(groups = {GroupsBeanValidation.CuisineIdValidation.class})
    @NotNull
    @DeliveryFeeAnnotation // Anotacao Composta Personalidade
    @MulitpleDeliveryFeeAnnotation(number = 2)
    //@PositiveOrZero(message = "{DeliveryFee.invalid}") // Busca no arquivo ValidationMessages.properties do Bean validation
        // (e nao no properties do SpringBoot), mas SpringBoot properties tem PREFERENCIA
    @Column(name="delivery_fee", nullable = false)
    private BigDecimal deliveryFee;

    /*
        Tudo que termina com ONE é EAGER Loading (fetch padrao)
     */
    //@JsonIgnore
    // @JsonIgnoreProperties({"hibernateLazyInitializer"}) // para ignora a falta de serializacao para essa propriedade quando usando LAZY no ToOne
    //@NotNull(groups = {GroupsBeanValidation.CuisineIdValidation.class})
    @NotNull
    @ConvertGroup(from = Default.class, to = GroupsBeanValidation.CuisineIdValidation.class) // faz a mesma coisa que
        // @NotNull(groups = {GroupsBeanValidation.CuisineIdValidation.class})
    @Valid // ==> Valida as propriedades dentro da Classe Cuisine
    @ManyToOne // (fetch = FetchType.LAZY)
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

    /*
        Tudo que termina com MANY é LAZY Loading (fetch padrao)
     */
    //@JsonIgnore
    @ManyToMany
    @JoinTable(name = "tbl_restaurant_paymenttype",
               joinColumns = @JoinColumn(name = "restaurant_id"),
               inverseJoinColumns = @JoinColumn(name = "payment_type_id"))
    private List<PaymentType> paymentTypeList = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

}
