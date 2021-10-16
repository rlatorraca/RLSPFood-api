package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.controller.CityController;
import ca.com.rlsp.rlspfoodapi.api.controller.ProvinceController;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProvinceOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProvinceModelAssembler extends RepresentationModelAssemblerSupport<Province, ProvinceOutputDto> {

    @Autowired
    private ModelMapper modelMapper;

    public ProvinceModelAssembler() {
        super(ProvinceController.class, ProvinceOutputDto.class);
    }


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

    @Override
    public ProvinceOutputDto toModel(Province province) {
        /* Usa o RepresentationModelAssemblerSupport<> que ja implementa _self link*/
        ProvinceOutputDto provinceOutputDto = createModelWithId(province.getId(), province);
        modelMapper.map(province, provinceOutputDto);

        provinceOutputDto.add(
                linkTo(ProvinceController.class)
                        .withRel("provinces")
        );


        return provinceOutputDto;
    }

    @Override
    public CollectionModel<ProvinceOutputDto> toCollectionModel(Iterable<? extends Province> provinces) {
        return super.toCollectionModel(provinces)
                .add(linkTo(ProvinceController.class).withSelfRel());
    }
}
