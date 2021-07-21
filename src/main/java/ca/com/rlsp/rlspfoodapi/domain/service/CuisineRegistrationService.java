package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundIntoDBException;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityIsForeignKeyException;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuisineRegistrationService {

    @Autowired
    private CuisineRepository cuisineRepository;

    public Cuisine save(Cuisine cuisine){
        return cuisineRepository.save(cuisine);
    }

    public void remove(Long id) {
        try {
            cuisineRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundIntoDBException(
                    String.format("Cuisine as code is %d not found into the Database", id)
            );
        } catch (DataIntegrityViolationException e){
            throw new EntityIsForeignKeyException(
                    String.format("Cuisine as code is %d cannot be removed, because that is being used as  secondary key", id)
            );
        }

    }

    public List<Cuisine> listAll(){
        return cuisineRepository.findAll();
    }

    public Optional<Cuisine> findById(Long id){
        try{
            return  cuisineRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundIntoDBException(
                    String.format("Cuisine as code is %d not found into the Database", id)
            );
        }
    }
}
