package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.PaymentTypeModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.PaymentTypeInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.PaymentTypeInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PaymentTypeOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.PaymentType;
import ca.com.rlsp.rlspfoodapi.domain.repository.PaymentTypeRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.PaymentTypeResgistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/paymenttype")
public class PaymentTypeController {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    private PaymentTypeResgistrationService paymentTypeResgistrationService;

    @Autowired
    private PaymentTypeModelAssembler paymentTypeModelAssembler;

    @Autowired
    private PaymentTypeInputDisassembler paymentTypeInputDisassembler;

    @GetMapping
    public List<PaymentTypeOutputDto> listar() {
        List<PaymentType> todasFormasPagamentos = paymentTypeRepository.findAll();

        return paymentTypeModelAssembler.fromControllerToOutputList(todasFormasPagamentos);
    }

    @GetMapping("/{paymentTypeId}")
    public PaymentTypeOutputDto buscar(@PathVariable Long paymentTypeId) {
        PaymentType formaPagamento = paymentTypeResgistrationService.findAndFail(paymentTypeId);

        return paymentTypeModelAssembler.fromControllerToOutput(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentTypeOutputDto adicionar(@RequestBody @Valid PaymentTypeInputDto paymentTypeInputDto) {
        PaymentType formaPagamento = paymentTypeInputDisassembler.fromInputToController(paymentTypeInputDto);

        formaPagamento = paymentTypeResgistrationService.save(formaPagamento);

        return paymentTypeModelAssembler.fromControllerToOutput(formaPagamento);
    }

    @PutMapping("/{paymentTypeId}")
    public PaymentTypeOutputDto atualizar(@PathVariable Long paymentTypeId,
                                         @RequestBody @Valid PaymentTypeInputDto paymentTypeInputDto) {
        PaymentType currentPaymentType = paymentTypeResgistrationService.findAndFail(paymentTypeId);

        paymentTypeInputDto.setId(paymentTypeId);
        paymentTypeInputDisassembler.fromDTOtoPaymentType(paymentTypeInputDto, currentPaymentType);

        currentPaymentType = paymentTypeResgistrationService.save(currentPaymentType);

        return paymentTypeModelAssembler.fromControllerToOutput(currentPaymentType);
    }

    @DeleteMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        paymentTypeResgistrationService.delete(formaPagamentoId);
    }
}
