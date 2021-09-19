package ca.com.rlsp.rlspfoodapi.api.disassembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CuisineInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProductPhotoInput;
import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.model.ProductPhoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductPhotoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public ProductPhoto fromInputToController(ProductPhotoInput productPhotoInput) {
        return modelMapper.map(productPhotoInput, ProductPhoto.class);
    }
    
    public void fromDTOtoProductPhoto(ProductPhotoInput productPhotoInput, ProductPhoto productPhoto) {
        modelMapper.map(productPhotoInput, productPhoto);
    }   
} 