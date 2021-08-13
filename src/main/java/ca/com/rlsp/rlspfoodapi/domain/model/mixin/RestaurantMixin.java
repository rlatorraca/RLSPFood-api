package ca.com.rlsp.rlspfoodapi.domain.model.mixin;

import ca.com.rlsp.rlspfoodapi.core.validation.GroupsBeanValidation;
import ca.com.rlsp.rlspfoodapi.domain.model.Address;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.model.PaymentType;
import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
    Faz c configuracao / conexao na Classe (RESTAURANT) em relacao as anotacoes do JACKSON (@Json*)
 */
public abstract class RestaurantMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cuisine cuisine;

    @JsonIgnore
    private Address address;

    @JsonIgnore
    private LocalDateTime createdDate;

    //@JsonIgnore
    private LocalDateTime dateLastUpdate;

    //@JsonIgnore
    private List<PaymentType> paymentTypeList ;

    @JsonIgnore
    private List<Product> products ;
}
