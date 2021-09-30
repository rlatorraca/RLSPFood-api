package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.PaymentTypeModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.PaymentTypeInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.PaymentTypeInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.PaymentTypeOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.PaymentType;
import ca.com.rlsp.rlspfoodapi.domain.repository.PaymentTypeRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.PaymentTypeRegistrationService;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    /*
        => GET All payment types returning Cache-Control to the Client App
     */
    @GetMapping
    public ResponseEntity<List<PaymentTypeOutputDto>> listAll() {
        List<PaymentType> todasFormasPagamentos = paymentTypeRepository.findAll();

        List<PaymentTypeOutputDto> paymentTypeOutputDtos = paymentTypeModelAssembler
                .fromControllerToOutputList(todasFormasPagamentos);
        return ResponseEntity
                .ok()
                .cacheControl(
                        //CacheControl.noCache()) // SEMPRE fara a validacao do Cache (como se sempre estivesse no estado STALE)
                        //CacheControl.noSore()) // SEM Cache (nao armazena qualquer tipo de Cache)
                        CacheControl
                                .maxAge(10, TimeUnit.SECONDS) // Cache to Client (10 s in this case)
                                .cachePrivate() // Nao permit cache em Proxy Reverso (apenas local, na maquina do cliente)
                                //.cachePublic() // Permite TODOS tipos de Cache (local e compartilhado)

                )
                .body(paymentTypeOutputDtos);


    }

    /*
         => GET 1 payment types returning Cache-Control to the Client App
     */

    @GetMapping("/{paymentTypeId}")
    public ResponseEntity<PaymentTypeOutputDto> findById(@PathVariable Long paymentTypeId) {
        PaymentType formaPagamento = paymentTypeRegistrationService.findOrFail(paymentTypeId);

        PaymentTypeOutputDto paymentTypeOutputDto = paymentTypeModelAssembler.fromControllerToOutput(formaPagamento);

        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(paymentTypeOutputDto);
    }

    /* => Regular GET
    @GetMapping
    public List<PaymentTypeOutputDto> listAll() {
        List<PaymentType> todasFormasPagamentos = paymentTypeRepository.findAll();

        return paymentTypeModelAssembler.fromControllerToOutputList(todasFormasPagamentos);
    }
     */

    /* => Regular GET 1 payment Type
    @GetMapping("/{paymentTypeId}")
    public PaymentTypeOutputDto (@PathVariable Long paymentTypeId) {
        PaymentType formaPagamento = paymentTypeRegistrationService.findOrFail(paymentTypeId);

        return paymentTypeModelAssembler.fromControllerToOutput(formaPagamento);
    }

     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentTypeOutputDto save(@RequestBody @Valid PaymentTypeInputDto paymentTypeInputDto) {
        PaymentType formaPagamento = paymentTypeInputDisassembler.fromInputToController(paymentTypeInputDto);

        formaPagamento = paymentTypeRegistrationService.save(formaPagamento);

        return paymentTypeModelAssembler.fromControllerToOutput(formaPagamento);
    }

    @PutMapping("/{paymentTypeId}")
    public PaymentTypeOutputDto update(@PathVariable Long paymentTypeId,
                                         @RequestBody @Valid PaymentTypeInputDto paymentTypeInputDto) {
        PaymentType currentPaymentType = paymentTypeRegistrationService.findOrFail(paymentTypeId);

        paymentTypeInputDto.setId(paymentTypeId);
        paymentTypeInputDisassembler.fromDTOtoPaymentType(paymentTypeInputDto, currentPaymentType);

        currentPaymentType = paymentTypeRegistrationService.save(currentPaymentType);

        return paymentTypeModelAssembler.fromControllerToOutput(currentPaymentType);
    }

    @DeleteMapping("/{paymentTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long formaPagamentoId) {
        paymentTypeRegistrationService.delete(formaPagamentoId);
    }
}
