package ca.com.rlsp.rlspfoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityIsForeignKeyException extends RuntimeException{

    public EntityIsForeignKeyException(String msg){
        super(msg);
    }
}
