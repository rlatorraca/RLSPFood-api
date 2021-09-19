package ca.com.rlsp.rlspfoodapi.domain.repository;


import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProductPhotoInput;
import ca.com.rlsp.rlspfoodapi.domain.model.ProductPhoto;

public  interface ProductRepositoryQueries {

    ProductPhoto save(ProductPhoto productPhoto);
}
