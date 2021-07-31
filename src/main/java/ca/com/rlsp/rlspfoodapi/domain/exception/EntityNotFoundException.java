package ca.com.rlsp.rlspfoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)// , reason="Entity not found")
public class EntityNotFoundException extends ResponseStatusException {

    // Quando informamos o HttpStatus qualquer
    public EntityNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }

    // Caso nao facemos o HttpStatus o padrao sera o HttpStatus.NOTFOUND
    public EntityNotFoundException(String reason){
        this(HttpStatus.NOT_FOUND, reason);
    }
}