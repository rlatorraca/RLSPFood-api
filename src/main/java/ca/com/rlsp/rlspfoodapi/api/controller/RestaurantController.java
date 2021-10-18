package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.RestaurantModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.RestaurantInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.RestaurantInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.RestaurantOutputDto;
import ca.com.rlsp.rlspfoodapi.api.openapi.controller.RestaurantControllerOpenApi;
import ca.com.rlsp.rlspfoodapi.core.validation.ValidationPatchException;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GenericBusinessException;
import ca.com.rlsp.rlspfoodapi.domain.exception.RestaurantNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.repository.RestaurantRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.RestaurantRegistrationService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

// Libera o CORS do navegador para essas ORIGIN
//@CrossOrigin(origins = { "https://www.rlspfood.local:8000", "https://www.rlspfood.ca:8000"} )
@RestController
@RequestMapping(path="/restaurants",  produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestaurantController implements RestaurantControllerOpenApi {

    private RestaurantRegistrationService restaurantRegistrationService;

    private RestaurantRepository restaurantRepository;

    // Para fazer a validacao dentro no PATCH (on validateMerge())
    private SmartValidator smartValidator;

    // Monta um DTO para os Model (de Entidade) para Restaurante
    private RestaurantModelAssembler restaurantModelAssembler;

    // Desmont um DTO para oumModel (de Entidade) para Restaurante
    private RestaurantInputDisassembler restaurantInputDisassembler;

    public RestaurantController(RestaurantRegistrationService restaurantRegistrationService,
                                RestaurantRepository restaurantRepository,
                                SmartValidator smartValidator,
                                RestaurantModelAssembler restaurantModelAssembler,
                                RestaurantInputDisassembler restaurantInputDisassembler) {
        this.restaurantRegistrationService = restaurantRegistrationService;
        this.restaurantRepository = restaurantRepository;
        this.smartValidator = smartValidator;
        this.restaurantModelAssembler = restaurantModelAssembler;
        this.restaurantInputDisassembler = restaurantInputDisassembler;
    }
    /*
        Limitando os campos retornados pela API com @JsonFilter do Jackson
     */

    /*
        Rrojeção de recursos com @JsonView do Jackson
     */

    @GetMapping
    public List<RestaurantOutputDto> listAll() {
        return restaurantModelAssembler.fromControllerToOutputList(restaurantRepository.newlistAll());
    }


    @GetMapping(params = "summary=summary")
    public List<RestaurantOutputDto> listAllSummary() {
        return listAll();
    }

    @GetMapping(params = "summary=justName")
    public List<RestaurantOutputDto> listAllJustNames() {
        return listAll();
    }

//	@GetMapping
//	public MappingJacksonValue listAll(@RequestParam(required = false) String summaryLevel) {
//		List<Restaurant> restaurants = restaurantRepository.findAll();
//		List<RestaurantOutputDto> restaurantsModel = restaurantModelAssembler.fromControllerToOutputList(restaurants);
//
//		MappingJacksonValue restaurantsWrapper = new MappingJacksonValue(restaurantsModel);
//
//        restaurantsWrapper.setSerializationView(RestaurantView.Summary.class);
//
//		if ("justName".equals(summaryLevel)) {
//            restaurantsWrapper.setSerializationView(RestaurantView.SummaryJustName.class);
//		} else if ("full".equals(summaryLevel)) {
//            restaurantsWrapper.setSerializationView(null);
//		}
//
//		return restaurantsWrapper;
//	}

//    @GetMapping
//    public List<RestaurantOutputDto> listAll() {
//        return restaurantModelAssembler.fromControllerToOutputList(restaurantRepository.newlistAll());
//    }
//
//    @JsonView(RestaurantView.Summary.class) // Alternativa ao DTOs
//    @GetMapping(params = "summary=level1")
//    public List<RestaurantOutputDto> listAllSummary() {
//       return listAll();
//    }
//
//    @JsonView(RestaurantView.SummaryJustName.class) // Alternativa ao DTOs
//    @GetMapping(params = "summary=level2")
//    public List<RestaurantOutputDto> listAllSummaryJustName() {
//        return listAll();
//    }

    /*
    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> findBy1Id(@PathVariable("restaurantId") Long id){
        Optional<Restaurant> restaurant =  restaurantRegistrationService.findById(id);

        if(restaurant.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(restaurant.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
    */
    @GetMapping("/{restaurantId}")
    public RestaurantOutputDto findById(@PathVariable("restaurantId") Long id){
        Restaurant restaurant = restaurantRegistrationService.findOrFail(id);

        RestaurantOutputDto restaurantDTO = restaurantModelAssembler.fromControllerToOutput(restaurant);

        //return restaurantRegistrationService.findOrFail(id);
        return restaurantDTO;
    }



    /*
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantRegistrationService.save(restaurant);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurant);
        } catch (EntityNotFoundIntoDBException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //public Restaurant save(@RequestBody @Validated(GroupsBeanValidation.RestaurantValidation.class) Restaurant restaurant) {
    public RestaurantOutputDto save(@RequestBody @Valid RestaurantInputDto restaurantInputDTO) {
       try {
           Restaurant restaurant = restaurantInputDisassembler.fromInputToController(restaurantInputDTO); // Converte da representacao de INPUT par ao MODEL

           return restaurantModelAssembler.fromControllerToOutputPost(restaurantRegistrationService.save(restaurant));
       } catch (EntityNotFoundException e ){
           throw new GenericBusinessException(e.getReason());
       }
    }


    /*
    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> updateById(@PathVariable("restauranteId") Long id, @RequestBody Restaurant restaurant){

        try{
            Optional<Restaurant> currentRestaurant = restaurantRegistrationService.findById(id);

            if(currentRestaurant.isPresent()){
                BeanUtils.copyProperties(restaurant, currentRestaurant.get(),"id", "paymentTypeList", "address", "createdDate", "products");
                Restaurant newRestaurant = restaurantRegistrationService.save(currentRestaurant.get());

                return ResponseEntity.ok(newRestaurant);
            }

            return ResponseEntity.notFound().build();
        }catch (EntityNotFoundIntoDBException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    */
    @PutMapping("/{restauranteId}")
    public RestaurantOutputDto updateById(@PathVariable("restauranteId") Long id, @RequestBody @Valid RestaurantInputDto restaurantInputDTO) {

        try {
            Restaurant currentRestaurant = restaurantRegistrationService.findOrFail(id);

            //Restaurant restaurant = restaurantInputDisassembler.fromInputToController(restaurantInputDTO);
            restaurantInputDTO.setId(id);
            restaurantInputDisassembler.fromDTOtoRestaurant(restaurantInputDTO, currentRestaurant);

            //BeanUtils.copyProperties(restaurant, currentRestaurant,"id", "paymentTypeList", "address", "createdDate", "products");
            return restaurantModelAssembler.fromControllerToOutput(restaurantRegistrationService.save(currentRestaurant));

        } catch (EntityNotFoundException  e ){
            throw new GenericBusinessException(e.getReason());
        }
    }

    /*
    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> updateByIdPatch(@PathVariable("restauranteId") Long id,
                                        @RequestBody Map<String, Object> restaurantFields){
        try{
            Optional<Restaurant> currentRestaurant = restaurantRegistrationService.findById(id);

            if(currentRestaurant.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Restaurant newRestaurant = mergeDataToPatch(restaurantFields, currentRestaurant.get());

            return updateById(id, newRestaurant);
        } catch (EntityNotFoundIntoDBException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }
    */
    @PatchMapping("/{restauranteId}")
    public RestaurantOutputDto updateByIdPatch(@PathVariable("restauranteId") Long id,
                                               @RequestBody Map<String, Object> restaurantFields,
                                               HttpServletRequest request){
        Restaurant currentRestaurant = restaurantRegistrationService.findOrFail(id);
        RestaurantInputDto currentRestaurantDTO = restaurantModelAssembler.fromControllerToInput(currentRestaurant);

        mergeDataToPatch(restaurantFields, currentRestaurantDTO, request);
        validateMerge(currentRestaurant, "restaurant");
        
        return updateById(id, currentRestaurantDTO);
    }

    // Faz a validacao de  modo programatico do PATCH
    private void validateMerge(Restaurant currentRestaurant, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(currentRestaurant, objectName );
        smartValidator.validate(currentRestaurant, bindingResult);

        // Verfica se tem ERRORS
        if(bindingResult.hasErrors()){
            throw  new ValidationPatchException(bindingResult);
        }
    }

    private RestaurantInputDto mergeDataToPatch(Map<String, Object> restaurantFields,
                                                RestaurantInputDto restaurantFinal,
                                                HttpServletRequest request) {

        ServletServerHttpRequest servletRequest = new ServletServerHttpRequest(request);

        /*
            Do Jackson (JSON <-> Objetct)
             - faz a criacao / mapeamento de um Objeto (no caso Restaurante) usando com base no MAP passado
         */
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true); // Para lancar Exception quando atributo JSON for ignorado
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true); // Para lancar Exception quando atributo JSON nao existir

            //Restaurant restaurantBase = objectMapper.convertValue(restaurantFields, Restaurant.class);
            RestaurantInputDto restaurantBase = objectMapper.convertValue(restaurantFields, RestaurantInputDto.class);

        /*
            O Reflection, em poucas palavras, serve para determinar métodos e atributos que serão utilizados de
            determinada classe (que você nem conhece) em tempo de execução. Há inúmeras utilidades para esse tipo
            de tarefa, podemos citar por exemplo a instalação de plugins adicionais ao nosso software desenvolvido.
         */
            restaurantFields.forEach( (nameAttribute, valueAttribute) -> {

                Field field = ReflectionUtils.findField(RestaurantInputDto.class, nameAttribute); // Java Reflection
                field.setAccessible(true);

                Object newValueToAttributeInRestaurant = ReflectionUtils.getField(field, restaurantBase);
                ReflectionUtils.setField(field, restaurantFinal, newValueToAttributeInRestaurant);
                //System.out.println(nameAttrinbute + " - " + valueAttribute + " <> " + newValueToAttributeInRestaurant);
            });
        } catch (IllegalArgumentException e){
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletRequest);
        }


        return restaurantFinal;
    }

    /*
        PUT /restaurant/{id}/active
         Usa-se PUT e nao POST. Pois o PUT e idempontente e o POST nao é idempotente (causa efeitos colaterais, muda o resultado)
     */
    @PutMapping("{restauranteId}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable("restauranteId") Long id) {
        restaurantRegistrationService.activate(id);
    }


    /*
        Ativa e Desativa uma Lista de Restaurants ao mesmo tempo
     */

    @PutMapping("/list-activation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateMultiplesRestaurants(@RequestBody List<Long> restaurantsIds) {
        try{
            restaurantRegistrationService.activeListOfRestaurantsService(restaurantsIds);
        } catch (RestaurantNotFoundException e){
            throw new GenericBusinessException(e.getReason(), e);
        }
    }

    @DeleteMapping("/list-deactivation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateMultiplesRestaurants(@RequestBody List<Long> restaurantsIds) {
        try {
        restaurantRegistrationService.inactiveListOfRestaurantsService(restaurantsIds);
        } catch (RestaurantNotFoundException e){
            throw new GenericBusinessException(e.getReason(), e);
        }
    }


    /*
        Routes for OPEN and CLOSE Restaurant
     */
    @PutMapping("/{restaurantId}/opening")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void openRestaurant(@PathVariable Long restaurantId) {
        restaurantRegistrationService.openRestaurantService(restaurantId);
    }

    @PutMapping("/{restaurantId}/closing")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void closeRestaurant(@PathVariable Long restaurantId) {
        restaurantRegistrationService.closeRestaurantService(restaurantId);
    }

    /*
        DELETE /restaurant/{id}/active
     */
    @DeleteMapping("/{restauranteId}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivate(@PathVariable("restauranteId") Long id) {
        restaurantRegistrationService.inactivate(id);
    }

}
