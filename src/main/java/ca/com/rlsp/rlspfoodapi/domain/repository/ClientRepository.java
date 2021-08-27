package ca.com.rlsp.rlspfoodapi.domain.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CustomJpaRepository<Client, Long> {


}
