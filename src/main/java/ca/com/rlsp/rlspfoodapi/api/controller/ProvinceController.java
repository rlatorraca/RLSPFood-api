package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.domain.exception.EntityIsForeignKeyException;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundIntoDBException;
import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import ca.com.rlsp.rlspfoodapi.domain.repository.ProvinceRepository;
import ca.com.rlsp.rlspfoodapi.domain.service.ProvinceRegistrationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceRegistrationService provinceRegistrationService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Province> listAllJson(){
        return provinceRegistrationService.listAll();
    }

    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE})
    public List<Province> listAllXml(){
        return provinceRegistrationService.listAll();
    }

    @GetMapping("/{provinceId}")
    public ResponseEntity<Province> findById(@PathVariable("provinceId") Long id) {
        Optional<Province> province = provinceRegistrationService.findById(id);

        if (province.isPresent()) {
            return ResponseEntity.ok(province.get());
        }

        return ResponseEntity.notFound().build();


    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Province save(@RequestBody Province province) {
        return provinceRegistrationService.save(province);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Province> updateById(@PathVariable("estadoId") Long id,
                                            @RequestBody Province province) {
        Optional<Province> currentProvince = provinceRegistrationService.findById(id);

        if (currentProvince != null) {
            BeanUtils.copyProperties(province, currentProvince.get(), "id");

            Province newProvince = provinceRegistrationService.save(currentProvince.get());
            return ResponseEntity.ok(newProvince);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable("estadoId") Long id) {
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
}
