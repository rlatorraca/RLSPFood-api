package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
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
    public CityOutputDto fromControllerToOutput(City city) {

        return modelMapper.map(city, CityOutputDto.class); // Mas o mapeamento substituindo o codigo acima
    }


    /*
        Convert MODEL -> DTO
    */
    public CityInputDto fromControllerToInput(City city) {
        CityInputDto cityInputDTO = new CityInputDto();

        ProvinceInputDto provinceInputDTO = new ProvinceInputDto();
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
    public List<CityOutputDto> fromControllerToOutputList(List<City> cities){
        return cities.stream()
                .map(city -> fromControllerToOutput(city))
                .collect(Collectors.toList());
    }
}
