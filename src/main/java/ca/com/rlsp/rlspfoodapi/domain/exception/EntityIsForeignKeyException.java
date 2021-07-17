package ca.com.rlsp.rlspfoodapi.domain.exception;

public class EntityIsForeignKeyException extends RuntimeException{

    public EntityIsForeignKeyException(String msg){
        super(msg);
    }
}
