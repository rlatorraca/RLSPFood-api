package ca.com.rlsp.rlspfoodapi.domain.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;

import java.util.List;

public interface ProvinceRepository {

    List<Province> listAll();
    Province findById(Long id);
    Province save(Province province);
    void remove(Long id);
}
