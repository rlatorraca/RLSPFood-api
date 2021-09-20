package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.ProductPhotoModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProductPhotoInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProductPhotoOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import ca.com.rlsp.rlspfoodapi.domain.model.ProductPhoto;
import ca.com.rlsp.rlspfoodapi.domain.service.CatalogueProductPhoto;
import ca.com.rlsp.rlspfoodapi.domain.service.ProductRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @Autowired
    private ProductRegistrationService productRegistrationService;

    @Autowired
    private CatalogueProductPhoto catalogueProductPhoto;



    @Autowired
    private ProductPhotoModelAssembler productPhotoModelAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoOutputDto updatePhoto(@PathVariable Long restaurantId,
                                             @PathVariable Long productId,
                                             @Valid ProductPhotoInputDto photoProductInput)  {

        Product product = productRegistrationService.findOrFail(restaurantId, productId);

        MultipartFile file = photoProductInput.getFile();

        ProductPhoto photo = new ProductPhoto();
        photo.setProduct(product);
        photo.setDescription(photoProductInput.getDescription());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());
        photo.setFileName(file.getOriginalFilename());

        ProductPhoto savedPhoto = catalogueProductPhoto.save(photo);

        return productPhotoModelAssembler.fromControllerToOutput(savedPhoto);
    }

    /* Old Version
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updatePhoto(@PathVariable Long restaurantId,
                            @PathVariable Long productId,
                            @Valid ProductPhotoInput photoProductInput)  {

        String fileName = UUID.randomUUID().toString() + "_" + photoProductInput.getFile().getOriginalFilename();

        Path photoFile = Path.of("src/main/resources/photos/saved/",fileName);

        try {
            photoProductInput.getFile().transferTo(photoFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    */

}


