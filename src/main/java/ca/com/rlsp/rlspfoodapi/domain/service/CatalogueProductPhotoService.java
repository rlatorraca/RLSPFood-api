package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.domain.model.ProductPhoto;
import ca.com.rlsp.rlspfoodapi.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogueProductPhotoService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PhotoStorageService photoStorageService;

    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto, InputStream fileData) {
        // Delete photo , if exists
        Long restaurantId = productPhoto.getRestaurantId();
        Long productId = productPhoto.getProduct().getId();
        Optional<ProductPhoto> existentPhoto = productRepository.findProductPhotoById(restaurantId, productId);
        String uuidFileName = photoStorageService.generateUUIDFileName(productPhoto.getFileName());

        if(existentPhoto.isPresent()) {
            productRepository.delete(existentPhoto.get());
        }

        // Salva a foto no DB
        productPhoto.setFileName(uuidFileName); // new name using UUID
        productPhoto = productRepository.save(productPhoto);
        productRepository.flush();;

        PhotoStorageService.NewPhoto newPhoto = PhotoStorageService
                .NewPhoto.builder()
                .newFIle(productPhoto.getFileName())
                .inputStream(fileData).build();

        // Salva a foto no Disco Local
        photoStorageService.storage(newPhoto);
        return productPhoto;
    }
}
