package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProductOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductModelAssembler {

    @Autowired
    private ModelMapper modelMapper;


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
}
