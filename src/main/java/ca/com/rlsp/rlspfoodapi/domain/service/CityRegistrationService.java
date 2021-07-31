package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.domain.exception.EntityIsForeignKeyException;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundIntoDBException;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
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

    public static final String MSG_CITY_AS_CODE_IS_NOT_FOUND_INTO_DATABASE = "City as code is %d not found into the Database";
    public static final String MSG_CITY_CANNOT_BE_REMOVED_USED_AS_SECONDARY_KEY = "City as code is %d cannot be removed, because that is being used as  secondary key";
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
                                String.format(MSG_CITY_AS_CODE_IS_NOT_FOUND_INTO_DATABASE, provinceId)
                        )
                );

       return cityRepository.save(city);
    }

    public void remove(Long id){
        try{
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundIntoDBException(
                    String.format(MSG_CITY_AS_CODE_IS_NOT_FOUND_INTO_DATABASE, id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntityIsForeignKeyException(
                    String.format(MSG_CITY_CANNOT_BE_REMOVED_USED_AS_SECONDARY_KEY, id)
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
                    String.format(MSG_CITY_AS_CODE_IS_NOT_FOUND_INTO_DATABASE, id)
            );
        }
    }

    public City findOrFail(Long id){
        return cityRepository.findById(id).orElseThrow(()-> new EntityNotFoundIntoDBException(String.format(MSG_CITY_AS_CODE_IS_NOT_FOUND_INTO_DATABASE, id)));
    }
}

