package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.domain.model.Province;
import ca.com.rlsp.rlspfoodapi.domain.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceRepository provinceRepository;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Province> listAllJson(){
        return provinceRepository.listAll();
    }

    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE})
    public List<Province> listAllXml(){
        return provinceRepository.listAll();
    }
}
