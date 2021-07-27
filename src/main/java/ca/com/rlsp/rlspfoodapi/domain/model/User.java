package ca.com.rlsp.rlspfoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_password", nullable = true)
    private String password;

    @CreationTimestamp
    @Column(name = "created_user", nullable = true, columnDefinition = "datetime")
    private LocalDateTime createdUser;

    @UpdateTimestamp
    @Column(name = "last_modified_user", nullable = false, columnDefinition = "datetime")
    private LocalDateTime lastModifiedUser;

    @ManyToMany
    @JoinTable(name = "tbl_user_group",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns =@JoinColumn(name = "groups_id")
    )
    private List<Group> groups = new ArrayList<>();

}
