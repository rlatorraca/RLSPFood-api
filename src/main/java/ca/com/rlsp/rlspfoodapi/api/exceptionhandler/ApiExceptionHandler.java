package ca.com.rlsp.rlspfoodapi.api.exceptionhandler;

import ca.com.rlsp.rlspfoodapi.domain.exception.EntityIsForeignKeyException;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/*
    ResponseEntityExceptionHandler
     - Classe de conveniencia que trata Exception do SPRINGBOOT
 */
@ControllerAdvice // faz com todas excecoes sejam tratadas nessa Classe
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_MEDIA_TYPE_NOT_SUPPORTED = "Media Type not supported on RLSPFood";
    public static final String MALFORMED_JSON_REQUEST = "Malformed JSON request. Check JSON syntax";
    public static final String MALFORMED_URI_REQUEST = "Malformed URI request. Check URI syntax";

    /*
          Metodo que trata a Excecao Generica e Cria uma mensagem de Erro Customizada
     */
    // WebRequest request => injetado pelo proprio SPRINGBOOT
    @ExceptionHandler({EntityNotFoundException.class})
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
        String detail = e.getReason().toString();

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

    @Override
    protected ResponseEntity<Object>  handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        // Busca a RAIZ do problema (da formacao do JSON)
        // Throwable rootCause = e.getRootCause();
        Throwable rootCause = ExceptionUtils.getRootCause(e);

        if(rootCause instanceof InvalidFormatException){
            return handleInvalidJSONFormatException((InvalidFormatException) rootCause, headers, status, request);
        }
        if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        if (rootCause instanceof UnrecognizedPropertyException) {
            return handlePropertyBindingException((UnrecognizedPropertyException) rootCause, headers, status, request);
        }
        ProblemTypeEnum problemType = ProblemTypeEnum.MALFORMED_JSON_REQUEST;
        String detail = MALFORMED_JSON_REQUEST;

        ApiHandleProblemDetail apiHandleProblem = createProblemDetailBuilder(status, problemType, detail, LocalDateTime.now()).build();

        return super.handleExceptionInternal(e, apiHandleProblem, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleInvalidJSONFormatException(InvalidFormatException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(e.getPath());

        ProblemTypeEnum problemType = ProblemTypeEnum.MALFORMED_JSON_REQUEST;
        String detail = String.format("Attribute '%s' has got the value '%s', that is invalid type. Inform a compatible value having type as %s.",
                path, e.getValue(), e.getTargetType().getSimpleName());

        ApiHandleProblemDetail apiHandleProblem = createProblemDetailBuilder(status, problemType, detail, LocalDateTime.now()).build();

        return handleExceptionInternal(e, apiHandleProblem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException e,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        // Criei o método joinPath para reaproveitar em todos os métodos que precisam
        // concatenar os nomes das propriedades (separando por ".")
        String path = joinPath(e.getPath());

        ProblemTypeEnum problemType = ProblemTypeEnum.MALFORMED_JSON_REQUEST;
        String detail = String.format("Attribute '%s' can't be treated by API. Fix it  or remove that attribute e try again.", path);

        ApiHandleProblemDetail apiHandleProblem = createProblemDetailBuilder(status, problemType, detail, LocalDateTime.now()).build();

        return handleExceptionInternal(e, apiHandleProblem, headers, status, request);
    }

    private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException e,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        // Criei o método joinPath para reaproveitar em todos os métodos que precisam
        // concatenar os nomes das propriedades (separando por ".")
        String path = joinPath(e.getPath());

        ProblemTypeEnum problemType = ProblemTypeEnum.MALFORMED_JSON_REQUEST;
        String detail = String.format("Attribute '%s' doesn't exist. Fix it  or remove that attribute e try again.", path);

        ApiHandleProblemDetail apiHandleProblem = createProblemDetailBuilder(status, problemType, detail, LocalDateTime.now()).build();

        return handleExceptionInternal(e, apiHandleProblem, headers, status, request);
    }

    private String joinPath(List<Reference> references) {
        references.forEach(r-> System.out.println(r.toString()));
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }


    private ApiHandleProblemDetail.ApiHandleProblemDetailBuilder createProblemDetailBuilder(
            HttpStatus status, ProblemTypeEnum problemType, String detail, LocalDateTime localDateTime){

        return ApiHandleProblemDetail.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .dateTime(localDateTime);

    }

    


}
