package ca.com.rlsp.rlspfoodapi.api.openapi.model;

import io.swagger.annotations.ApiModelProperty;

public class PagedModelOpenApi {

    @ApiModelProperty(example = "1", value="Number of registry by page")
    private Long size;
    @ApiModelProperty(example = "100", value="Number total of elements")
    private Long totalElements;
    @ApiModelProperty(example = "10", value="Number total of pages")
    private Long totalPages;
    @ApiModelProperty(example = "1", value="Number of pages (statts in 0")
    private Long number;
}
