package ca.com.rlsp.rlspfoodapi.domain.exception;


public class RestaurantNotFoundException extends EntityNotFoundException {

    public static final String MSG_RESTAURANT_IS_NOT_FOUND_DATABASE = "Restaurant as code is %d not found into the Database";

    public RestaurantNotFoundException(String msg){
        super(msg);
    }

    public RestaurantNotFoundException(Long provinceId) {
        super(String.format(MSG_RESTAURANT_IS_NOT_FOUND_DATABASE, provinceId));
    }
}