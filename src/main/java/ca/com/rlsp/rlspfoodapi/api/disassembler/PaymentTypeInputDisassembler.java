package ca.com.rlsp.rlspfoodapi.api.disassembler;

import ca.com.rlsp.rlspfoodapi.api.model.dto.input.CityInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.PaymentTypeInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PaymentTypeOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.PaymentType;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentTypeInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;


    public PaymentType fromInputToController(PaymentTypeInputDto paymentTypeInputDto) {
        return modelMapper.map(paymentTypeInputDto, PaymentType.class);
    }

    public void fromDTOtoPaymentType(PaymentTypeInputDto paymentTypeInputDto, PaymentType paymentType) {
        modelMapper.map(paymentTypeInputDto, paymentType);
    }
}
