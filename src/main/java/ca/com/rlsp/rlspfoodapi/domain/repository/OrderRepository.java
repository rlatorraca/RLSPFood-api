package ca.com.rlsp.rlspfoodapi.domain.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
