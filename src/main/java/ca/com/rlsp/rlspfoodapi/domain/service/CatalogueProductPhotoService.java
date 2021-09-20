package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.domain.model.ProductPhoto;
import ca.com.rlsp.rlspfoodapi.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CatalogueProductPhotoService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto) {
        // Delete photo , if exists
        Long restaurantId = productPhoto.getRestaurantId();
        Long productId = productPhoto.getProduct().getId();
        Optional<ProductPhoto> existentPhoto = productRepository.findProductPhotoById(restaurantId, productId);

        if(existentPhoto.isPresent()) {
            productRepository.delete(existentPhoto.get());
        }
        return productRepository.save(productPhoto);
    }
}
