package ca.com.rlsp.rlspfoodapi.domain.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.infra.repository.customized.RestaurantRepositoryImplQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends
                CustomJpaRepository<Restaurant, Long>,
                RestaurantRepositoryImplQueries,
                JpaSpecificationExecutor {

    @Query("from Restaurant r inner join fetch r.cuisine left join fetch r.paymentTypeList")
    List<Restaurant> newlistAll();

    List<Restaurant> findBydeliveryFeeBetween(BigDecimal initial, BigDecimal end);
    List<Restaurant> findByNameStartingWithAndCuisineId(String restaurant, Long cuisineId);
    List<Restaurant> findTop2RestaurantsByNameContaining(String name);

    Optional<Restaurant> findFirstRestaurantByNameContaining(String name);

    // This query is into resources/META-INF/orm.xml
    //@Query("from Restaurant where name like %:name% and cuisine.id=:id")
    List<Restaurant> consultarPorNomePorCozinha(String name, @Param("id") Long cozinhaId);


    /*
        Customizado
        public List<Restaurant> procurarRestauranteNasFaixas(
            String name, BigDecimal startFee, BigDecimal endFee);
     */
}
