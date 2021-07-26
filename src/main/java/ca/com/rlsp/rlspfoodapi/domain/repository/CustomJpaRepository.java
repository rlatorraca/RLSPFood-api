package ca.com.rlsp.rlspfoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean // Igonara a instanciacao da implemeentaocao desse Bean
public interface CustomJpaRepository<T,ID> extends JpaRepository<T, ID> {

    Optional<T> findFirstElement();
}
