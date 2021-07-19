package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.domain.exception.EntityIsForeignKeyException;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundIntoDBException;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import ca.com.rlsp.rlspfoodapi.domain.repository.CityRepository;
import ca.com.rlsp.rlspfoodapi.domain.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceRegistrationService {

    @Autowired
    private ProvinceRepository provinceRepository;

    public Province save(Province Province){
        return provinceRepository.save(Province);
    }

    public void remove(Long id){
        try{
            provinceRepository.remove(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundIntoDBException(
                    String.format("Province as code is %d not found into the Database", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntityIsForeignKeyException(
                    String.format("Province as code is %d cannot be removed, because that is being used as  secondary key", id)
            );
        }
    }

    public List<Province> listAll(){
        return provinceRepository.listAll();
    }

    public Province findById(Long id){
        try{
            return  provinceRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundIntoDBException(
                    String.format("Province as code % dis not found into the Database", id)
            );
        }
    }
}
