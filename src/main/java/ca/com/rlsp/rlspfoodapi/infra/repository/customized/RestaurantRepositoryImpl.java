package ca.com.rlsp.rlspfoodapi.infra.repository.customized;

import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

/*
    Implementacao de Repositorio de Restaurante Customizado
 */
@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryImplQueries {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Restaurant> procurarRestauranteNasFaixas(
            String name, BigDecimal startFee, BigDecimal endFee){

        var jpql = "from Restaurant where name like :nome and deliveryFee between :start and :end";

        return em.createQuery(jpql, Restaurant.class)
                .setParameter("nome", "%" + name + "%")
                .setParameter("start", startFee)
                .setParameter("end", endFee)
                .getResultList();
    }

}
