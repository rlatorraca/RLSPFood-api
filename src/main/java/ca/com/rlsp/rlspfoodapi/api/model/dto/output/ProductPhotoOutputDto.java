package ca.com.rlsp.rlspfoodapi.api.model.dto.output;


import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoOutputDto {


    @ApiModelProperty(example = "59a0dd1e-8dea-4cac-b5f8-34d82bdaa099_1_1_picanha.jpeg")
    private String fileName;
    @ApiModelProperty(example = "Top Sirloin cap")
    private String description;
    @ApiModelProperty(example = "image/jpeg")
    private String contentType;
    @ApiModelProperty(example = "1024")
    private Long size;

}
