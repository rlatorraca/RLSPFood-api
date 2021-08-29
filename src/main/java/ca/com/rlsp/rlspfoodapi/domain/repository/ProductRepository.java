package ca.com.rlsp.rlspfoodapi.domain.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CustomJpaRepository<Product, Long> {

    @Query("from Product where restaurant.id = :restaurant and id = :product")
    Optional<Product> findById(@Param("restaurant") Long restaurantId,
                               @Param("product") Long productId);

    List<Product> findByRestaurant(Restaurant restaurant);

}
