package ca.com.rlsp.rlspfoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@EqualsAndHashCode
@Data
@Entity
@Table(name = "tbl_cuisine")
public class Cuisine {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

}