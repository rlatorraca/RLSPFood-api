package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CuisineInputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.RestaurantInputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProvinceOutputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.RestaurantOutputDTO;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProvinceModelAssembler {

    @Autowired
    private ModelMapper modelMapper;


    /*
        Convert MODEL -> DTO para PUT
    */
    public ProvinceOutputDTO fromControllerToOutput(Province province) {

        return modelMapper.map(province, ProvinceOutputDTO.class); // Mas o mapeamento substituindo o codigo acima
    }


    /*
        Convert MODEL -> DTO
    */
    public ProvinceInputDTO fromControllerToInput(Province province) {
        ProvinceInputDTO provinceInputDTO = new ProvinceInputDTO();

        provinceInputDTO.setId(province.getId());
        provinceInputDTO.setName(province.getName());

        return provinceInputDTO;
    }


    /*
        Convert MODEL -> DTO (list GET)
    */
    public List<ProvinceOutputDTO> fromControllerToOutputList(List<Province> provinces){
        return provinces.stream()
                .map(province -> fromControllerToOutput(province))
                .collect(Collectors.toList());
    }
}
