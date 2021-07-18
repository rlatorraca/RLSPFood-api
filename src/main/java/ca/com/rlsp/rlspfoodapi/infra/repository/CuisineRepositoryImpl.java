package ca.com.rlsp.rlspfoodapi.infra.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.repository.CuisineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CuisineRepositoryImpl implements CuisineRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cuisine> listAll(){
        TypedQuery<Cuisine> query = em.createQuery("from Cuisine", Cuisine.class);
        return query.getResultList();
    }

    @Override
    public Cuisine findById(Long id){
        Cuisine cuisine = em.find(Cuisine.class, id);

        return cuisine;
    }

    @Override
    @Transactional
    public Cuisine save(Cuisine cuisine){
        return em.merge(cuisine);
    }

    @Override
    @Transactional
    public void remove(Long id){
        Cuisine  cuisine =  findById(id);
        if(cuisine == null){
            throw new EmptyResultDataAccessException(1);
        }
        em.remove(cuisine);
    }
}
