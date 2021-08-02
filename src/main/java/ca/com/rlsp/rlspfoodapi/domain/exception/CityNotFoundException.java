package ca.com.rlsp.rlspfoodapi.domain.exception;


public class CityNotFoundException extends EntityNotFoundException {

    public static final String MSG_CITY_IS_NOT_FOUND_DATABASE = "City as code is %d not found into the Database";

    public CityNotFoundException(String msg){
        super(msg);
    }

    public CityNotFoundException(Long provinceId) {
        super(String.format(MSG_CITY_IS_NOT_FOUND_DATABASE, provinceId));
    }
}