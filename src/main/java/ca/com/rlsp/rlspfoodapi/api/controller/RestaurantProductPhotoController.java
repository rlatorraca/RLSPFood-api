package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.ProductPhotoModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProductPhotoInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProductPhotoOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import ca.com.rlsp.rlspfoodapi.domain.model.ProductPhoto;
import ca.com.rlsp.rlspfoodapi.domain.service.CatalogueProductPhotoService;
import ca.com.rlsp.rlspfoodapi.domain.service.PhotoStorageService;
import ca.com.rlsp.rlspfoodapi.domain.service.ProductRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @Autowired
    private ProductRegistrationService productRegistrationService;

    @Autowired
    private CatalogueProductPhotoService catalogueProductPhotoService;

    @Autowired
    private PhotoStorageService photoStorageService;



    @Autowired
    private ProductPhotoModelAssembler productPhotoModelAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPhotoOutputDto findProductPhoto(@PathVariable Long restaurantId,
                                                  @PathVariable Long productId) {
        ProductPhoto productPhoto = catalogueProductPhotoService
                .findAndFail(restaurantId, productId);

        return productPhotoModelAssembler.fromControllerToOutput(productPhoto);
    }

    @GetMapping(produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<InputStreamResource> getProductPhoto(@PathVariable Long restaurantId,
                                          @PathVariable Long productId) {
        try {
            ProductPhoto productPhoto = catalogueProductPhotoService
                    .findAndFail(restaurantId, productId);

            InputStream inputStream = photoStorageService.recovery(productPhoto.getFileName());


            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(inputStream));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoOutputDto updatePhoto(@PathVariable Long restaurantId,
                                             @PathVariable Long productId,
                                             @Valid ProductPhotoInputDto photoProductInput) throws IOException {

        Product product = productRegistrationService.findOrFail(restaurantId, productId);

        MultipartFile file = photoProductInput.getFile();

        ProductPhoto photo = new ProductPhoto();
        photo.setProduct(product);
        photo.setDescription(photoProductInput.getDescription());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());
        photo.setFileName(file.getOriginalFilename());

        ProductPhoto savedPhoto = catalogueProductPhotoService.save(photo, file.getInputStream());

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


