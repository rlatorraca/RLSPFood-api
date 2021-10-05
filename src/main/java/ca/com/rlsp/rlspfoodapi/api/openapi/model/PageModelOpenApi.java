package ca.com.rlsp.rlspfoodapi.api.openapi.model;

import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CuisineOutputDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
/*
    Classe Generia para Subsitituir Pages de Todos os controllers
 */

@ApiModel(value = "PageModel")
@Getter
@Setter
public class PageModelOpenApi<T>{
    private Collection<T> content;
    @ApiModelProperty(example = "1", value="Number of registry by page")
    private Long size;
    @ApiModelProperty(example = "100", value="Number total of elements")
    private Long totalElements;
    @ApiModelProperty(example = "10", value="Number total of pages")
    private Long totalPages;
    @ApiModelProperty(example = "1", value="Number of pages (statts in 0")
    private Long number;
}
