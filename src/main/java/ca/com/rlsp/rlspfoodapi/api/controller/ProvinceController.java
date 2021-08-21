package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.api.assembler.ProvinceModelAssembler;
import ca.com.rlsp.rlspfoodapi.api.disassembler.ProvinceInputDisassembler;
import ca.com.rlsp.rlspfoodapi.api.model.dto.input.ProvinceInputDto;
import ca.com.rlsp.rlspfoodapi.api.model.dto.output.ProvinceOutputDto;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import ca.com.rlsp.rlspfoodapi.domain.repository.ProvinceRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.ProvinceRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceRegistrationService provinceRegistrationService;

    @Autowired
    private ProvinceInputDisassembler provinceInputDisassembler;

    @Autowired
    private ProvinceModelAssembler provinceModelAssembler;

    @Autowired
    private ProvinceRepository provinceRepository;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    //public List<Province> listAllJson(){
    public List<ProvinceOutputDto> listAllJson(){

        //return provinceRegistrationService.listAll();
        List<Province> allProvinces = provinceRegistrationService.listAll();

        return provinceModelAssembler.fromControllerToOutputList(allProvinces);
    }

    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE})
    public List<Province> listAllXml(){
        return provinceRegistrationService.listAll();
    }

    /*
    @GetMapping("/{provinceId}")
    public ResponseEntity<Province> findById(@PathVariable("provinceId") Long id) {
        Optional<Province> province = provinceRegistrationService.findById(id);

        if (province.isPresent()) {
            return ResponseEntity.ok(province.get());
        }

        return ResponseEntity.notFound().build();
    }
    */
    @GetMapping("/{provinceId}")
    //public Province findById(@PathVariable("provinceId") Long id) {
    public ProvinceOutputDto findById(@PathVariable("provinceId") Long id) {
        //return provinceRegistrationService.findOrFail(id);
        Province province = provinceRegistrationService.findOrFail(id);

        return provinceModelAssembler.fromControllerToOutput(province);

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //public Province save(@RequestBody @Valid Province province) {
    public ProvinceOutputDto save(@RequestBody @Valid ProvinceInputDto provinceInputDTO) {
        //return provinceRegistrationService.save(province);
        Province province = provinceInputDisassembler.fromInputToController(provinceInputDTO);
        return provinceModelAssembler.fromControllerToOutput(province);
    }


    /*
    @PutMapping("/{provinceId}")
    public ResponseEntity<Province> updateById(@PathVariable("provinceId") Long id,
                                            @RequestBody Province province) {
        Optional<Province> currentProvince = provinceRegistrationService.findById(id);

        if (currentProvince != null) {
            BeanUtils.copyProperties(province, currentProvince.get(), "id");

            Province newProvince = provinceRegistrationService.save(currentProvince.get());
            return ResponseEntity.ok(newProvince);
        }

        return ResponseEntity.notFound().build();
    }
    */
    @PutMapping("/{provinceId}")
    //public Province updateById(@PathVariable("provinceId") Long id, @RequestBody @Valid Province province) {
    public ProvinceOutputDto updateById(@PathVariable("provinceId") Long id, @RequestBody @Valid ProvinceInputDto provinceInputDTO) {
        Province currentProvince = provinceRegistrationService.findOrFail(id);

        provinceInputDTO.setId(id);
        provinceInputDisassembler.fromDTOtoProvince(provinceInputDTO, currentProvince);

        currentProvince = provinceRegistrationService.save(currentProvince);
        //BeanUtils.copyProperties(province, currentProvince, "id");
        //return provinceRegistrationService.save(currentProvince);

        return provinceModelAssembler.fromControllerToOutput(currentProvince);
    }

    /*
    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remove(@PathVariable("estadoId") Long id) {
        try {
            provinceRegistrationService.remove(id);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundIntoDBException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityIsForeignKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
    */

    @DeleteMapping("/{provinceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long provinceId) {
        provinceRepository.deleteById(provinceId);
    }
}
