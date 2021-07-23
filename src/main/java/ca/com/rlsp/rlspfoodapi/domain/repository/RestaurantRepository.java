package ca.com.rlsp.rlspfoodapi.domain.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.infra.repository.customized.RestaurantRepositoryImplQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryImplQueries {

    List<Restaurant> findByDeliveryFeeBetween(BigDecimal initial, BigDecimal end);
    List<Restaurant> findByNameStartingWithAndCuisineId(String restaurant, Long cuisineId);
    List<Restaurant> findByNameStartingWith(@RequestParam BigDecimal initial, @RequestParam BigDecimal end);
    List<Restaurant> findTop2RestaurantsByNameContaining(String name);
    Optional<Restaurant> findFirstRestaurantByNameContaining(String name);


    /*
        Customizado
        public List<Restaurant> procurarRestauranteNasFaixas(
            String name, BigDecimal startFee, BigDecimal endFee);
     */
}
