package ca.com.rlsp.rlspfoodapi.infra.repository.customized.old;

import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.infra.repository.customized.RestaurantRepositoryImplQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/*
    Implementacao de Repositorio de Restaurante Customizado
 */
//@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryImplQueries {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Restaurant> procurarRestauranteNasFaixas(String name,
                                      BigDecimal startFee, BigDecimal endFee){

//        var jpql = new StringBuilder();
//        var parameters = new HashMap<String, Object>();
//
//        jpql.append("from Restaurant where 0 = 0 ");
//
//        if(StringUtils.hasLength(name)){
//            jpql.append("and name like :nome ");
//            parameters.put("nome", "%" + name + "%");
//        }
//
//        if(startFee != null){
//            jpql.append("and deliveryFee >= :start ");
//            parameters.put("start", startFee);
//        }
//
//        if(endFee != null){
//            jpql.append("and deliveryFee <= :end )");
//            parameters.put("end", endFee);
//        }
//
//        TypedQuery<Restaurant> query =  em.createQuery(jpql.toString(), Restaurant.class);
//        parameters.forEach((key, value) -> query.setParameter(key , value));
//        return query.getResultList();

        var jpql = new StringBuilder();
        jpql.append("from Restaurant where 0 = 0 ");

        var parametros = new HashMap<String, Object>();

        if (name != null && StringUtils.hasLength(name)) {
            jpql.append("and name like :nome ");
            parametros.put("nome", "%" + name + "%");
        }

        if (startFee != null) {
            jpql.append("and deliveryFee >= :taxaInicial ");
            parametros.put("taxaInicial", startFee);
        }

        if (endFee != null) {
            jpql.append("and deliveryFee <= :taxaFinal ");
            parametros.put("taxaFinal", endFee);
        }

        TypedQuery<Restaurant> query = em
                .createQuery(jpql.toString(), Restaurant.class);

        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

        return query.getResultList();
    }

}
