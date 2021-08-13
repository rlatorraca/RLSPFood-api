package ca.com.rlsp.rlspfoodapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

/*
    Usa-se o Builder um padrao de projeto que nao deixar fazer a instanciocao da Classe, em vez disso usamos
        o padrao builder (um cosntructor) de projeto
 */

/*
    RFC 7807 (Problem Details for HTTP APIs)
 */
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiHandleProblemDetail {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private OffsetDateTime dateTime;

    // EXTENDING => MSG For USERS (FrontEnd)
    private String userMessage;
    private List<Object> objects;

    @Getter
    @Setter
    @Builder
    public static class Object {
        private String name;
        private String userMessage;
    }


}
