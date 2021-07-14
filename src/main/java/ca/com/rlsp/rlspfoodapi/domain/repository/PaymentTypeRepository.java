package ca.com.rlsp.rlspfoodapi.domain.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.PaymentType;

import java.util.List;

public interface PaymentTypeRepository {

    List<PaymentType> listAll();
    PaymentType findById(Long id);
    PaymentType save(PaymentType paymentType);
    void remove(PaymentType paymentType);
}
