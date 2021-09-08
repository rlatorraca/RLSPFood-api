package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.PaymentTypeModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.PaymentTypeInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.PaymentTypeInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PaymentTypeOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.PaymentType;
import ca.com.rlsp.rlspfoodapi.domain.repository.PaymentTypeRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.PaymentTypeRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/paymenttype")
public class PaymentTypeController {

    private PaymentTypeRepository paymentTypeRepository;
    private PaymentTypeRegistrationService paymentTypeRegistrationService;
    private PaymentTypeModelAssembler paymentTypeModelAssembler;
    private PaymentTypeInputDisassembler paymentTypeInputDisassembler;

    public PaymentTypeController(PaymentTypeRepository paymentTypeRepository,
                                 PaymentTypeRegistrationService paymentTypeRegistrationService,
                                 PaymentTypeModelAssembler paymentTypeModelAssembler,
                                 PaymentTypeInputDisassembler paymentTypeInputDisassembler) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.paymentTypeRegistrationService = paymentTypeRegistrationService;
        this.paymentTypeModelAssembler = paymentTypeModelAssembler;
        this.paymentTypeInputDisassembler = paymentTypeInputDisassembler;
    }

    @GetMapping
    public List<PaymentTypeOutputDto> listar() {
        List<PaymentType> todasFormasPagamentos = paymentTypeRepository.findAll();

        return paymentTypeModelAssembler.fromControllerToOutputList(todasFormasPagamentos);
    }

    @GetMapping("/{paymentTypeId}")
    public PaymentTypeOutputDto buscar(@PathVariable Long paymentTypeId) {
        PaymentType formaPagamento = paymentTypeRegistrationService.findOrFail(paymentTypeId);

        return paymentTypeModelAssembler.fromControllerToOutput(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentTypeOutputDto adicionar(@RequestBody @Valid PaymentTypeInputDto paymentTypeInputDto) {
        PaymentType formaPagamento = paymentTypeInputDisassembler.fromInputToController(paymentTypeInputDto);

        formaPagamento = paymentTypeRegistrationService.save(formaPagamento);

        return paymentTypeModelAssembler.fromControllerToOutput(formaPagamento);
    }

    @PutMapping("/{paymentTypeId}")
    public PaymentTypeOutputDto atualizar(@PathVariable Long paymentTypeId,
                                         @RequestBody @Valid PaymentTypeInputDto paymentTypeInputDto) {
        PaymentType currentPaymentType = paymentTypeRegistrationService.findOrFail(paymentTypeId);

        paymentTypeInputDto.setId(paymentTypeId);
        paymentTypeInputDisassembler.fromDTOtoPaymentType(paymentTypeInputDto, currentPaymentType);

        currentPaymentType = paymentTypeRegistrationService.save(currentPaymentType);

        return paymentTypeModelAssembler.fromControllerToOutput(currentPaymentType);
    }

    @DeleteMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        paymentTypeRegistrationService.delete(formaPagamentoId);
    }
}
