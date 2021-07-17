package ca.com.rlsp.rlspfoodapi.infra.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.PaymentType;
import ca.com.rlsp.rlspfoodapi.domain.repository.PaymentTypeRepository;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class PaymentTypeRepositoryImpl implements PaymentTypeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<PaymentType> listAll() {
        TypedQuery<PaymentType> query = em.createQuery("from PaymentType", PaymentType.class);
        return query.getResultList();
    }

    @Override
    public PaymentType findById(Long id) {
        PaymentType paymentType = em.find(PaymentType.class, id);
        if(paymentType == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return paymentType;
    }

    @Override
    public PaymentType save(PaymentType paymentType) {
        return em.merge(paymentType);
    }

    @Override
    public void remove(Long id) {
        PaymentType paymentType =  findById(id);
        if(paymentType == null){
            throw new EmptyResultDataAccessException(1);
        }
        em.remove(paymentType);
    }
}
