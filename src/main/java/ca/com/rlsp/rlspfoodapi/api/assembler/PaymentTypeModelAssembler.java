package ca.com.rlsp.rlspfoodapi.api.assembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.CityOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PaymentTypeOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.PaymentType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PaymentTypeModelAssembler {

    @Autowired
    private ModelMapper modelMapper;


    /*
        Convert MODEL -> DTO para PUT
    */
    public PaymentTypeOutputDto fromControllerToOutput(PaymentType paymentType) {

        return modelMapper.map(paymentType, PaymentTypeOutputDto.class); // Faz o mapeamento substituindo o codigo acima
    }


    /*
        Convert MODEL -> DTO
    */
    public PaymentTypeOutputDto fromControllerToInput(PaymentType paymentType) {
        PaymentTypeOutputDto paymentTypeOutputDto = new PaymentTypeOutputDto();

        paymentTypeOutputDto.setId(paymentType.getId());
        paymentTypeOutputDto.setName(paymentType.getName());

        return paymentTypeOutputDto;
    }


    /*
        Convert MODEL -> DTO (list GET)
    */
    public List<PaymentTypeOutputDto> fromControllerToOutputList(Collection<PaymentType> paymentTypes){
        return paymentTypes.stream()
                .map(paymentType -> fromControllerToOutput(paymentType))
                .collect(Collectors.toList());
    }
    /*
    public Set<PaymentTypeOutputDto> fromControllerToOutputList(Set<PaymentType> paymentTypes){
        return paymentTypes.stream()
                .map(paymentType -> fromControllerToOutput(paymentType))
                .collect(Collectors.toSet());
    }
     */
}
