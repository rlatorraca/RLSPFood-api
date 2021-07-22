package ca.com.rlsp.rlspfoodapi.domain.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long> {


    List<Cuisine> findByNameContaining(String name);
    Optional<Cuisine> findByName(String name);
    List<Cuisine> findByNameStartingWith(String name);
    List<Cuisine> findByNameEndingWith(String name);


}
