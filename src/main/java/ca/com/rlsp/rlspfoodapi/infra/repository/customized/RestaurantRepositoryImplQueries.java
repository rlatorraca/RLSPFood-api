package ca.com.rlsp.rlspfoodapi.infra.repository.customized;

import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryImplQueries {

    List<Restaurant> procurarRestauranteNasFaixas(
            String name, BigDecimal startFee, BigDecimal endFee);
}
