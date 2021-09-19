package ca.com.rlsp.rlspfoodapi.api.model.dto.output;


import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoOutputDto {

    private Long id;

    private String fileName;
    private String description;
    private String contentType;
    private Long size;

    private Product product;
}
