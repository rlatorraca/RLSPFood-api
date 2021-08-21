package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProvinceOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
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
    public ProvinceOutputDto fromControllerToOutput(Province province) {

        return modelMapper.map(province, ProvinceOutputDto.class); // Mas o mapeamento substituindo o codigo acima
    }


    /*
        Convert MODEL -> DTO
    */
    public ProvinceInputDto fromControllerToInput(Province province) {
        ProvinceInputDto provinceInputDTO = new ProvinceInputDto();

        provinceInputDTO.setId(province.getId());
        provinceInputDTO.setName(province.getName());

        return provinceInputDTO;
    }


    /*
        Convert MODEL -> DTO (list GET)
    */
    public List<ProvinceOutputDto> fromControllerToOutputList(List<Province> provinces){
        return provinces.stream()
                .map(province -> fromControllerToOutput(province))
                .collect(Collectors.toList());
    }
}
