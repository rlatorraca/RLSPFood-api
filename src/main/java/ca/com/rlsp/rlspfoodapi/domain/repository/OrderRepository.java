package ca.com.rlsp.rlspfoodapi.domain.repository;

import ca.com.rlsp.rlspfoodapi.api.assembler.OrderShortModelAssembler;
import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CustomJpaRepository<Order, Long> {

    Optional<Order> findByOrderCode(String ordercode);

    @Query("from Order o join fetch o.user join fetch o.restaurant r join fetch r.cuisine")
    public List<Order> findAll();
}
