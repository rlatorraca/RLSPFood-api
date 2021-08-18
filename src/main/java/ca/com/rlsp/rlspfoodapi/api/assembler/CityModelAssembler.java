package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDTO;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProvinceOutputDTO;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CityModelAssembler {

    @Autowired
    private ModelMapper modelMapper;


    /*
        Convert MODEL -> DTO para PUT
    */
    public CityOutputDTO fromControllerToOutput(City city) {

        return modelMapper.map(city, CityOutputDTO.class); // Mas o mapeamento substituindo o codigo acima
    }


    /*
        Convert MODEL -> DTO
    */
    public CityInputDTO fromControllerToInput(City city) {
        CityInputDTO cityInputDTO = new CityInputDTO();

        ProvinceInputDTO provinceInputDTO = new ProvinceInputDTO();
        provinceInputDTO.setId(city.getProvince().getId());
        provinceInputDTO.setName(city.getProvince().getName());

        cityInputDTO.setId(city.getId());
        cityInputDTO.setName(city.getName());
        cityInputDTO.setProvince(provinceInputDTO);

        return cityInputDTO;
    }


    /*
        Convert MODEL -> DTO (list GET)
    */
    public List<CityOutputDTO> fromControllerToOutputList(List<City> cities){
        return cities.stream()
                .map(city -> fromControllerToOutput(city))
                .collect(Collectors.toList());
    }
}
