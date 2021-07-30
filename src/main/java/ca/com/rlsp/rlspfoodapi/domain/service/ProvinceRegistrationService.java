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
public class ProvinceRegistrationService {

    public static final String MSG_PROVINCE_IS_NOT_FOUND_DATABASE = "Province as code is %d not found into the Database";
    public static final String MSG_PROVINCE_IS_USED_AS_SECONDARY_KEY = "Province as code is %d cannot be removed, because that is being used as  secondary key";
    @Autowired
    private ProvinceRepository provinceRepository;


    public Province save(Province province){
        return provinceRepository.save(province);
    }

    public void remove(Long id){
        try{
            provinceRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundIntoDBException(
                    String.format(MSG_PROVINCE_IS_NOT_FOUND_DATABASE, id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntityIsForeignKeyException(
                    String.format(MSG_PROVINCE_IS_USED_AS_SECONDARY_KEY, id)
            );
        }
    }

    public List<Province> listAll(){
        return provinceRepository.findAll();
    }

    public Optional<Province> findById(Long id){
        try{
            return  provinceRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundIntoDBException(
                    String.format("Province as code % dis not found into the Database", id)
            );
        }
    }

    public Province findOrFail(Long id){
        return provinceRepository.findById(id).orElseThrow(()-> new EntityNotFoundIntoDBException(String.format(MSG_PROVINCE_IS_NOT_FOUND_DATABASE, id)));
    }
}
