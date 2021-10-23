package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.controller.RestaurantProductPhotoController;
import ca.com.rlsp.rlspfoodapi.api.links.BuildLinks;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProductPhotoOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.ProductPhoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductPhotoModelAssembler extends RepresentationModelAssemblerSupport<ProductPhoto, ProductPhotoOutputDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildLinks buildLinks;

    public ProductPhotoModelAssembler() {
        super(RestaurantProductPhotoController.class, ProductPhotoOutputDto.class);
    }


    /*
        Convert MODEL -> DTO para PUT
    */
    public ProductPhotoOutputDto fromControllerToOutput(ProductPhoto photo) {

        return modelMapper.map(photo, ProductPhotoOutputDto.class); // Mas o mapeamento substituindo o codigo acima
    }


    @Override
    public ProductPhotoOutputDto toModel(ProductPhoto productPhoto) {
        //ProductPhotoOutputDto productPhotoOutputDto = createModelWithId(productPhoto.getId() , productPhoto);
        ProductPhotoOutputDto productPhotoOutputDto = modelMapper.map(productPhoto, ProductPhotoOutputDto.class);
        modelMapper.map(productPhoto, productPhotoOutputDto);

        productPhotoOutputDto.add(buildLinks.getLinkToPhotoProduct(productPhoto.getRestaurantId(), productPhoto.getProduct().getId()));
        productPhotoOutputDto.add(buildLinks.getLinkToPhotoProduct(productPhoto.getRestaurantId(), productPhoto.getProduct().getId(), "photo"));


        return productPhotoOutputDto;
    }
}
