package ca.com.rlsp.rlspfoodapi.api.exceptionhandler;

import ca.com.rlsp.rlspfoodapi.domain.exception.EntityIsForeignKeyException;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/*
    ResponseEntityExceptionHandler
     - Classe de conveniencia que trata Exception do SPRINGBOOT
 */
@ControllerAdvice // faz com todas excecoes sejam tratadas nessa Classe
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_MEDIA_TYPE_NOT_SUPPORTED = "Media Type not supported on RLSPFood";

    /*
          Metodo que trata a Excecao Generica e Cria uma mensagem de Erro Customizada
     */
    // WebRequest request => injetado pelo proprio SPRINGBOOT
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request){
        /*
        ApiHandleError handler = ApiHandleError.builder()
                .dateTime(LocalDateTime.now())
                .message(e.getReason())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(handler);
         */
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemTypeEnum problemType = ProblemTypeEnum.ENTITY_NOT_FOUND;
        String detail = e.getReason();

        ApiHandleProblemDetail apiHandleProblem = createProblemDetailBuilder(status, problemType, detail, LocalDateTime.now()).build();

        return handleExceptionInternal(e, apiHandleProblem, new HttpHeaders(), status, request);
    }

    // WebRequest request => injetado pelo proprio SPRINGBOOT
    @ExceptionHandler(GenericBusinessException.class)
    public ResponseEntity<?> handleGenericBusinessException(GenericBusinessException e, WebRequest request){
        /*
        ApiHandleError handler = ApiHandleError.builder()
                .dateTime(LocalDateTime.now())
                .message(e.getReason())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(handler);
         */

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemTypeEnum problemType = ProblemTypeEnum.BUSINESS_RULES_HAS_ERROR;
        String detail = e.getReason();

        ApiHandleProblemDetail apiHandleProblem = createProblemDetailBuilder(status, problemType, detail, LocalDateTime.now()).build();

        return handleExceptionInternal(e, apiHandleProblem, new HttpHeaders(), status, request);
    }

    // WebRequest request => injetado pelo proprio SPRINGBOOT
    @ExceptionHandler(EntityIsForeignKeyException.class)
    public ResponseEntity<?> handleEntityIsForeignKeyException(EntityIsForeignKeyException e, WebRequest request){
        /*
        ApiHandleError handler = ApiHandleError.builder()
                .dateTime(LocalDateTime.now())
                .message(e.getReason())
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(handler);
        */
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemTypeEnum problemType = ProblemTypeEnum.ENTITY_IN_USE;
        String detail = e.getReason();

        ApiHandleProblemDetail apiHandleProblem = createProblemDetailBuilder(status, problemType, detail, LocalDateTime.now()).build();

        return handleExceptionInternal(e, apiHandleProblem, new HttpHeaders(), status, request);
    }

    /*
        Exception do Proprio Spring (415 - HttpMediaTypeNotSupportedException)

        ** Necessario quando usamos *** extends ResponseEntityExceptionHandler ***

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> handleHttpMediaTypeNotSupportedException() {

        ApiHandleError handler = ApiHandleError.builder()
                .dateTime(LocalDateTime.now())
                .message(MSG_MEDIA_TYPE_NOT_SUPPORTED)
                .build();

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(handler);
    }
    */

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if(body == null){
            body = ApiHandleProblemDetail.builder()
                    .dateTime(LocalDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if(body instanceof String){
            body = ApiHandleProblemDetail.builder()
                    .dateTime(LocalDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .build();
        }
        return super.handleExceptionInternal(e, body , headers, status, request);
    }


    private ApiHandleProblemDetail.ApiHandleProblemDetailBuilder createProblemDetailBuilder(HttpStatus status, ProblemTypeEnum problemType, String detail, LocalDateTime localDateTime){

        return ApiHandleProblemDetail.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .dateTime(localDateTime);

    }
}
