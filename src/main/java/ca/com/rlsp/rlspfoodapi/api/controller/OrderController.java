package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.OrderModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.assembler.OrderShortModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.OrderInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.OrderInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.filter.OrderFilterInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.OrderOutputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.OrderShortOutputDto;
import ca.com.rlsp.rlspfoodapi.core.data.PageableTranslator;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.model.Order;
import ca.com.rlsp.rlspfoodapi.domain.model.User;
import ca.com.rlsp.rlspfoodapi.domain.repository.OrderRepository;
import ca.com.rlsp.rlspfoodapi.domain.repository.filter.OrderFilter;
import ca.com.rlsp.rlspfoodapi.domain.service.IssueOfOrderRegistrationService;
import ca.com.rlsp.rlspfoodapi.infra.repository.specification.OrderSpecifications;
import com.google.common.collect.ImmutableMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private OrderRepository orderRepository;
    private IssueOfOrderRegistrationService issueOfOrderRegistrationService;
    private OrderShortModelAssembler orderShortModelAssembler;
    private OrderModelAssembler orderModelAssembler;
    private OrderInputDisassembler orderInputDisassembler;

    public OrderController(OrderRepository orderRepository,
                           IssueOfOrderRegistrationService issueOfOrderRegistrationService,
                           OrderShortModelAssembler orderShortModelAssembler,
                           OrderModelAssembler orderModelAssembler,
                           OrderInputDisassembler orderInputDisassembler) {
        this.orderRepository = orderRepository;
        this.issueOfOrderRegistrationService = issueOfOrderRegistrationService;
        this.orderShortModelAssembler = orderShortModelAssembler;
        this.orderModelAssembler = orderModelAssembler;
        this.orderInputDisassembler = orderInputDisassembler;
    }

    /*
        Limitando os campos retornados pela API com @JsonFilter do Jackson
     */
//    @GetMapping
//    public MappingJacksonValue listAll(@RequestParam(required = false) String fields) {
//        List<Order> allOrders = orderRepository.findAll();
//        List<OrderShortOutputDto> allOrderOutputDto = orderShortModelAssembler.fromControllerToOutputList(allOrders);
//
//        MappingJacksonValue orderWrapper = new MappingJacksonValue(allOrderOutputDto);
//
//        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//        filterProvider.addFilter("orderFilter", SimpleBeanPropertyFilter.serializeAll());
//
//        if(StringUtils.isNotBlank(fields)){
//            filterProvider.addFilter("orderFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields.split(",")));
//        }
//
//        orderWrapper.setFilters(filterProvider);
//
//        return orderWrapper;
//    }

    /*
        Implementando PAGEABLE e SORTING dem ORDER (com filter)
     */
    @GetMapping("/filter-pageable")
    public Page<OrderOutputDto> searchByFilterPageable(OrderFilterInputDto orderFilter, @PageableDefault(size = 2) Pageable pageable) {
        // traduz fields de pageable para Order (fields)
        pageable = translatePageable(pageable);

        Page<Order> allOrders = orderRepository.findAll(OrderSpecifications.gettingByFilter(orderFilter), pageable);
        List<OrderOutputDto> orderOutputDtoList = orderModelAssembler.fromControllerToOutputList(allOrders.getContent());

        Page<OrderOutputDto> orderOutputDtoPage = new PageImpl<>(orderOutputDtoList, pageable, allOrders.getTotalPages());

        return orderOutputDtoPage;
    }

    /*
        Pesquisas complexas na API (by URL params)
     */

    @GetMapping("/filter")
    public List<OrderOutputDto> searchByFilter(OrderFilterInputDto orderFilter) {
        List<Order> allOrders = orderRepository.findAll(OrderSpecifications.gettingByFilter(orderFilter));

        return orderModelAssembler.fromControllerToOutputList(allOrders);
    }

   //  Standard Way
    @GetMapping
    public List<OrderOutputDto> listAll() {
        List<Order> allOrders = orderRepository.findAll();

        return orderModelAssembler.fromControllerToOutputList(allOrders);
    }

    @GetMapping("/{orderCode}")
    public OrderOutputDto find(@PathVariable String orderCode) {
        Order order = issueOfOrderRegistrationService.findOrFail(orderCode);

        return orderModelAssembler.fromControllerToOutput(order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderOutputDto add(@Valid @RequestBody OrderInputDto orderInputDto) {
        try {
            Order newOrder = orderInputDisassembler.fromInputToController(orderInputDto);

            // TODO get AUTHENTICATED User
            newOrder.setUser(new User());
            newOrder.getUser().setId(1L);

            newOrder = issueOfOrderRegistrationService.issue(newOrder);

            return orderModelAssembler.fromControllerToOutput(newOrder);
        } catch (EntityNotFoundException e) {
            throw new GenericBusinessException(e.getReason(), e);
        }
    }

    /*
        Faz a traducao de atributos que sao expostos no Pageable e nao estrao presentes dentro do Model (no caso Order)
        => Vai ser usado para o SORTING
     */
    private Pageable translatePageable(Pageable pageable) {
        // ** Poderiamos usar o Map.of (do Java)
        var mapping = ImmutableMap.of(
                "orderCode", "oderCode",
                "restaurant.name", "restaurant.name",
                "nameUser", "user.name",
                "valorTotal", "afterTaxes"
        );

        return PageableTranslator.translate(pageable, mapping);
    }

}
