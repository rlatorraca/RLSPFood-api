package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProductPhotoInput;
import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import ca.com.rlsp.rlspfoodapi.domain.model.ProductPhoto;
import ca.com.rlsp.rlspfoodapi.domain.service.CatalogueProductPhoto;
import ca.com.rlsp.rlspfoodapi.domain.service.ProductRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @Autowired
    private ProductRegistrationService productRegistrationService;

    @Autowired
    private CatalogueProductPhoto catalogueProductPhoto;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updatePhoto(@PathVariable Long restaurantId,
                            @PathVariable Long productId,
                            @Valid ProductPhotoInput photoProductInput)  {

        Product product = productRegistrationService.findOrFail(restaurantId, productId);

        ProductPhoto photo = new ProductPhoto();

        catalogueProductPhoto.save(photo);
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


