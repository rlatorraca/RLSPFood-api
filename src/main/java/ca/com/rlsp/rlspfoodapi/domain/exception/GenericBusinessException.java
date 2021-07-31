package ca.com.rlsp.rlspfoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class GenericBusinessException extends RuntimeException {

    public GenericBusinessException(String msg){
        super(msg);
    }

    public GenericBusinessException(String msg, Throwable cause){
        super(msg, cause);
    }
}