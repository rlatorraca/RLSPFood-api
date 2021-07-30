package ca.com.rlsp.rlspfoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)// , reason="Entity not found")
public class EntityNotFoundIntoDBException extends ResponseStatusException {

    // Quando informamos o HttpStatus qualquer
    public EntityNotFoundIntoDBException(HttpStatus status, String reason) {
        super(status, reason);
    }

    // Caso nao facemos o HttpStatus o padrao sera o HttpStatus.NOTFOUND
    public EntityNotFoundIntoDBException(String reason){
        this(HttpStatus.NOT_FOUND, reason);
    }
}