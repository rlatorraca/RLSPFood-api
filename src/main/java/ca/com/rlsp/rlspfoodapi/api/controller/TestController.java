package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/test")
public class TestController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @GetMapping("/cuisines/byname")
    public List<Cuisine> cuisineByName (@RequestParam("nome")  String name) {
        return cuisineRepository.findByName(name);
    }

    @GetMapping("/cuisines/contains")
    public List<Cuisine> cuisineContainInsName (@RequestParam("nome")  String name) {
        return cuisineRepository.findByNameContaining(name);
    }
    @GetMapping("/cuisines/starting")
    public Optional<Cuisine> cuisineStartingBy(@RequestParam("nome")  String name) {
        return cuisineRepository.findByNameStartingWith(name);
    }
}
