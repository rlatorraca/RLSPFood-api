package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.controller.RestaurantController;
import ca.com.rlsp.rlspfoodapi.api.controller.RestaurantProductController;
import ca.com.rlsp.rlspfoodapi.api.links.BuildLinks;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProductOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductOutputDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BuildLinks buildLinks;

    public ProductModelAssembler() {
        super(RestaurantProductController.class, ProductOutputDto.class);
    }


    /*
        Convert MODEL -> DTO para PUT
    */
    public ProductOutputDto fromControllerToOutput(Product product) {

        return modelMapper.map(product, ProductOutputDto.class); // Mas o mapeamento substituindo o codigo acima
    }


    /*
        Convert MODEL -> DTO
    */
    public ProductOutputDto fromControllerToInput(Product product) {
        ProductOutputDto productOutputDto = new ProductOutputDto();

        productOutputDto.setId(product.getId());
        productOutputDto.setName(product.getName());
        productOutputDto.setDescription(product.getDescription());
        productOutputDto.setPrice(product.getPrice());
        productOutputDto.setActive(product.getActive());


        return productOutputDto;
    }


    /*
        Convert MODEL -> DTO (list GET)
    */
    public List<ProductOutputDto> fromControllerToOutputList(List<Product> products){
        return products.stream()
                .map(product -> fromControllerToOutput(product))
                .collect(Collectors.toList());
    }

    /*
       Convert MODEL -> DTO (list GET)
   */
    public List<ProductOutputDto> fromControllerToOutputListActives(List<Product> products){
        return products.stream()
                .map(product -> fromControllerToOutput(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductOutputDto toModel(Product product) {

        ProductOutputDto productOutputDto = createModelWithId(product.getId(), product, product.getRestaurant().getId());
        //ProductOutputDto productOutputDto = new ProductOutputDto();
        modelMapper.map(product, productOutputDto);

        productOutputDto.add(buildLinks.getLinkToProducts(product.getRestaurant().getId(), "products"));


        return productOutputDto;
    }
}
