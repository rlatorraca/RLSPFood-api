package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProductPhotoInput;
import ca.com.rlsp.rlspfoodapi.domain.model.ProductPhoto;
import ca.com.rlsp.rlspfoodapi.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogueProductPhoto {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto) {
        return productRepository.save(productPhoto);
    }
}
