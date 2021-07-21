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
import java.util.Optional;

@Service
public class CityRegistrationService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    public City save(City city){
        Long provinceId = city.getProvince().getId();
        Province province = (Province) provinceRepository
                .findById(provinceId)
                .orElseThrow(
                        () -> new EntityNotFoundIntoDBException(
                                String.format("City as code is %d not saved into the Database", provinceId)
                        )
                );

       return cityRepository.save(city);
    }

    public void remove(Long id){
        try{
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundIntoDBException(
                    String.format("City as code is %d not found into the Database", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntityIsForeignKeyException(
                    String.format("City as code is %d cannot be removed, because that is being used as  secondary key", id)
            );
        }
    }

    public List<City> listAll(){
        return cityRepository.findAll();
    }

    public Optional<City> findById(Long id){
        try{
            return  cityRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundIntoDBException(
                    String.format("City as code % dis not found into the Database", id)
            );
        }
    }
}

