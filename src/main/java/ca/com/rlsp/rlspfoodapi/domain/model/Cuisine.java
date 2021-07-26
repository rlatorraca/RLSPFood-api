package ca.com.rlsp.rlspfoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


//@JsonRootName("gastronomy") // Mudar o nome do Ckasse em XML para outro nome
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "tbl_cuisine")
public class Cuisine {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;


    @JsonProperty("nome") // Muda o nome que vira no JSON quando consultado (diferentemente da Classe de Dominio)
    @Column(nullable = false, name="cuisine_name")
    private String name;

    @JsonIgnore /// remove da Apresentacao da Classe de Dominio na resposta (no Json de respota)
    @OneToMany(mappedBy = "cuisine")
    private List<Restaurant> restaurants = new ArrayList<>();


}
