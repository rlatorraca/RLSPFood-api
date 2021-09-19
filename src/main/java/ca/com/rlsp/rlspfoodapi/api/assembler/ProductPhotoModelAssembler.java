package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProductOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProductPhotoOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import ca.com.rlsp.rlspfoodapi.domain.model.ProductPhoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductPhotoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;


    /*
        Convert MODEL -> DTO para PUT
    */
    public ProductPhotoOutputDto fromControllerToOutput(ProductPhoto photo) {

        return modelMapper.map(photo, ProductPhotoOutputDto.class); // Mas o mapeamento substituindo o codigo acima
    }


    /*
        Convert MODEL -> DTO
    */
    public ProductPhotoOutputDto fromControllerToInput(ProductPhoto photo) {
        ProductPhotoOutputDto productPhotoOutputDto = new ProductPhotoOutputDto();

        productPhotoOutputDto.setId(photo.getId());
        productPhotoOutputDto.setContentType(photo.getContentType());
        productPhotoOutputDto.setDescription(photo.getDescription());
        productPhotoOutputDto.setFileName(photo.getFileName());
        productPhotoOutputDto.setSize(photo.getSize());
        productPhotoOutputDto.setProduct(photo.getProduct());


        return productPhotoOutputDto;
    }



}
