package ca.com.rlsp.rlspfoodapi.domain.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.infra.repository.customized.RestaurantRepositoryImplQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryImplQueries {

    List<Restaurant> findBydeliveryFeeBetween(BigDecimal initial, BigDecimal end);
    List<Restaurant> findByNameStartingWithAndCuisineId(String restaurant, Long cuisineId);

    /*
        Customizado

        public List<Restaurant> procurarRestauranteNasFaixas(
            String name, BigDecimal startFee, BigDecimal endFee);

     */
}


