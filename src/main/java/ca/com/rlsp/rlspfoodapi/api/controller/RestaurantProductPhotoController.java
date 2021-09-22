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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePhoto(@PathVariable Long restaurantId,
                            @PathVariable Long productId) {
        catalogueProductPhotoService.remove(restaurantId,productId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPhotoOutputDto findProductPhoto(@PathVariable Long restaurantId,
                                                  @PathVariable Long productId) {
        ProductPhoto productPhoto = catalogueProductPhotoService
                .findAndFail(restaurantId, productId);

        return productPhotoModelAssembler.fromControllerToOutput(productPhoto);
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> getProductPhoto(@PathVariable Long restaurantId,
                                          @PathVariable Long productId,
                                          @RequestHeader(name="accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        try {
            ProductPhoto productPhoto = catalogueProductPhotoService
                    .findAndFail(restaurantId, productId);

            // Pega o Media Type da PHOTO
            MediaType mediaTypePhoto = MediaType.parseMediaType(productPhoto.getContentType());
            // Pega a lista de Media Types aceitas pela aplicacao
            List<MediaType> mediaTypeList = MediaType.parseMediaTypes(acceptHeader);

            checkMediTypeCompatibility(mediaTypePhoto, mediaTypeList);

            InputStream inputStream = photoStorageService.recovery(productPhoto.getFileName());


            return ResponseEntity.ok()
                    .contentType(mediaTypePhoto)// Retorna o Media Type presente na Photo
                    .body(new InputStreamResource(inputStream));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void checkMediTypeCompatibility(MediaType mediaTypePhoto, List<MediaType> mediaTypeList) throws HttpMediaTypeNotAcceptableException {

        // verifica se pelo menos 1 Media Type e compativel para a foto buscada
        boolean compatible = mediaTypeList.stream()
                .anyMatch(mediaType -> mediaType.isCompatibleWith(mediaTypePhoto));

        if(!compatible) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypeList);
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

        ProductPhoto savedPhoto = catalogueProductPhotoService.savePhoto(photo, file.getInputStream());

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


