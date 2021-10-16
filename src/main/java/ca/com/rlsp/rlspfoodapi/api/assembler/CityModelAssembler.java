package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.controller.CityController;
import ca.com.rlsp.rlspfoodapi.api.controller.ProvinceController;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
public class CityModelAssembler extends RepresentationModelAssemblerSupport<City, CityOutputDto> {

    @Autowired
    private ModelMapper modelMapper;


    /* Obrigatorio por estender RepresentationModelAssemblerSupport*/
    public CityModelAssembler() {
        super(CityController.class, CityOutputDto.class);
    }

    /*
            Convert MODEL -> DTO para PUT
        */
    public CityOutputDto fromControllerToOutput(City city) {

        return modelMapper.map(city, CityOutputDto.class); // Mas o mapeamento substituindo o codigo acima
    }

    @Override // override a classe de RepresentationModelAssemblerSupport
    public CityOutputDto toModel(City city) {
        /* Usa o RepresentationModelAssemblerSupport<> que ja implementa _self link*/
        CityOutputDto cityOutputDto = createModelWithId(city.getId(), city);
        modelMapper.map(city, cityOutputDto);

        // Adiciona os links no recurso automaticamente (Standard)

//        CityOutputDto cityOutputDto = modelMapper.map(city, CityOutputDto.class);
//        cityOutputDto.add(
//                linkTo(methodOn(CityController.class)
//                        .findById(cityOutputDto.getId()))
//                        .withSelfRel()
//        );

        cityOutputDto.add(
                linkTo(methodOn(CityController.class)
                        .listAllJson())
                        .withRel("cities")
        );

        cityOutputDto.getProvince().add(
                linkTo(methodOn(ProvinceController.class)
                        .findById(cityOutputDto.getProvince().getId()))
                        .withSelfRel()
        );

        return cityOutputDto;
    }

    @Override
    public CollectionModel<CityOutputDto> toCollectionModel(Iterable<? extends City> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(CityController.class)
                        .withSelfRel()
                );
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

        ** ja existe um CollectionModel dentro de RepresentationModelAssemblerSupport
    */
//    public List<CityOutputDto> fromControllerToOutputList(List<City> cities){
//        return cities.stream()
//                .map(city -> fromControllerToOutput(city))
//                .collect(Collectors.toList());
//    }
}
