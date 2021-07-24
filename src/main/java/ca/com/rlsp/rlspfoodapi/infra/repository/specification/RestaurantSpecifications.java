package ca.com.rlsp.rlspfoodapi.infra.repository.specification;

import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.infra.repository.specification.old.RestaurantFindFreeDeliverySpecification;
import ca.com.rlsp.rlspfoodapi.infra.repository.specification.old.RestaurantFindNameLikeSpecification;
import org.springframework.data.jpa.domain.Specification;

public class RestaurantSpecifications {

    public static Specification<Restaurant> findFreeDelivereySpec(){
        return new RestaurantFindFreeDeliverySpecification();
    }

    public static Specification<Restaurant> findNameLikeSpec(String name){
       return new RestaurantFindNameLikeSpecification(name);
    }
}
