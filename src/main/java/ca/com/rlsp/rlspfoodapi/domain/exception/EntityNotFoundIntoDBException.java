package ca.com.rlsp.rlspfoodapi.domain.exception;

public class EntityNotFoundIntoDBException extends RuntimeException {

    public EntityNotFoundIntoDBException(String msg){
        super(msg);
    }
}
