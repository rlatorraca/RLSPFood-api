package ca.com.rlsp.rlspfoodapi.jpa;

import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.beans.Customizer;
import java.util.List;

@Component
public class ServiceCuisine {

    @PersistenceContext
    private EntityManager em;

    public List<Cuisine> listAll(){
        TypedQuery<Cuisine> query = em.createQuery("from Cuisine", Cuisine.class);
        return query.getResultList();
    }

    public Cuisine listById(Long id){
        return em.find(Cuisine.class, id);
    }

    @Transactional
    public Cuisine add(Cuisine cuisine){
        return em.merge(cuisine);
    }
}
