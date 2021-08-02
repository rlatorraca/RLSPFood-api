package ca.com.rlsp.rlspfoodapi.api.exceptionhandler;

import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice // faz com todas excecoes sejam tratadas nessa Classe
public class ApiExceptionHandler {

    public static final String MSG_MEDIA_TYPE_NOT_SUPPORTED = "Media Type not supported on RLSPFood";

    /*
               Metodo que trata a Excecao Generica e Cria uma mensagem de Errro Customizada
            */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e){
        ApiHandleError handler = ApiHandleError.builder()
                .dateTime(LocalDateTime.now())
                .message(e.getReason())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(handler);
    }

    @ExceptionHandler(GenericBusinessException.class)
    public ResponseEntity<?> handleGenericBusinessException(GenericBusinessException e){
        ApiHandleError handler = ApiHandleError.builder()
                .dateTime(LocalDateTime.now())
                .message(e.getReason())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(handler);
    }

    /*
        Exception do Proprio Spring (415 - HttpMediaTypeNotSupportedException)
     */

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> handleHttpMediaTypeNotSupportedException() {

        ApiHandleError handler = ApiHandleError.builder()
                .dateTime(LocalDateTime.now())
                .message(MSG_MEDIA_TYPE_NOT_SUPPORTED)
                .build();

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(handler);
    }
}
