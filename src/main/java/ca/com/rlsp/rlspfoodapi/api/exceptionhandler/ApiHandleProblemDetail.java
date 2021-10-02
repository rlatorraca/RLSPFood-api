package ca.com.rlsp.rlspfoodapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("ProblemDetail")
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiHandleProblemDetail {

    @ApiModelProperty(example = "400",position = 1)
    private Integer status;
    private String type;
    private String title;
    private String detail;
    @ApiModelProperty(example = "2021-11-14T00:12:13",position = 40)
    private OffsetDateTime dateTime;

    // EXTENDING => MSG For USERS (FrontEnd)
    private String userMessage;
    private List<Object> objects;

    @ApiModel("ObjectProblemDetail")
    @Getter
    @Setter
    @Builder
    public static class Object {
        @ApiModelProperty(example = "Province")
        private String name;
        @ApiModelProperty(example = "Province not found")
        private String userMessage;
    }


}
