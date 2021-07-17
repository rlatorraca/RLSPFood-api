package ca.com.rlsp.rlspfoodapi.domain.repository;

import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;

import java.util.List;

public interface CuisineRepository {

    List<Cuisine> listAll();
    Cuisine findById(Long id);
    Cuisine save(Cuisine cuisine);
    void remove(Long id);
}
