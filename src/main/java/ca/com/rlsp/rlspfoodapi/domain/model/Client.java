package ca.com.rlsp.rlspfoodapi.domain.model;

import ca.com.rlsp.rlspfoodapi.domain.repository.CustomJpaRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbl_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "client_name", nullable = false)
    private String name;

    @Column(name = "client_email", nullable = false)
    private String email;

    @Column(name = "client_password", nullable = true)
    private String password;

    @CreationTimestamp
    @Column(name = "client_created", nullable = true, columnDefinition = "datetime")
    private OffsetDateTime createdClient;

    @UpdateTimestamp
    @Column(name = "client_last_modified", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime lastModifiedClient;

    @ManyToMany
    @JoinTable(name = "tbl_client_group",
               joinColumns = @JoinColumn(name = "client_id"),
               inverseJoinColumns =@JoinColumn(name = "group_id")
    )
    private List<Group> groups = new ArrayList<>();

    public boolean passwordMatches(String password) {
        return getPassword().equals(password);
    }

    public boolean passwordNotMatches(String password) {
        return !passwordMatches(password);
    }


    }
