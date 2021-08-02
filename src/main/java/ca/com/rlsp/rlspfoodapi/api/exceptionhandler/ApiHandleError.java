package ca.com.rlsp.rlspfoodapi.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/*
    Usa-se o Builder um padrao de projeto que nao deixar fazer a instanciocao da Classe, em vez disso usamos
        o padrao builder (um cosntructor) de projeto
 */
@Builder
@Getter
public class ApiHandleError {

    private LocalDateTime dateTime;
    private String message;

}
