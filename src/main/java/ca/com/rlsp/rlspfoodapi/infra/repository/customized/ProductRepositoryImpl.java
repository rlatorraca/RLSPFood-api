package ca.com.rlsp.rlspfoodapi.infra.repository.customized;

import ca.com.rlsp.rlspfoodapi.api.assembler.ProductPhotoModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProductInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProductPhotoInput;
import ca.com.rlsp.rlspfoodapi.domain.model.ProductPhoto;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.repository.ProductRepositoryQueries;
import ca.com.rlsp.rlspfoodapi.domain.repository.RestaurantRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static ca.com.rlsp.rlspfoodapi.infra.repository.specification.RestaurantSpecifications.findFreeDelivereySpec;
import static ca.com.rlsp.rlspfoodapi.infra.repository.specification.RestaurantSpecifications.findNameLikeSpec;

/*
    Implementacao de Repositorio de Restaurante Customizado
 */
@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProductPhotoModelAssembler productPhotoModelAssembler;


    @Transactional
    @Override
    public ProductPhoto save(ProductPhoto productPhoto) {
        return em.merge(productPhoto);
    }

}
