package ca.com.rlsp.rlspfoodapi.domain.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;

import java.util.List;

public interface CityRepository {

    List<City> listAll();
    City findById(Long id);
    City save(City city);
    void remove(City city);
}
