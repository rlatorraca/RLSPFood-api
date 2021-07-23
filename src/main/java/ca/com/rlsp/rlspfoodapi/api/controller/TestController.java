package ca.com.rlsp.rlspfoodapi.api.controller;

import ca.com.rlsp.rlspfoodapi.domain.model.Cuisine;
import ca.com.rlsp.rlspfoodapi.domain.model.Restaurant;
import ca.com.rlsp.rlspfoodapi.domain.repository.CuisineRepository;
import ca.com.rlsp.rlspfoodapi.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/test")
public class TestController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/cuisines/byname")
    public Optional<Cuisine> cuisineByName (@RequestParam("nome")  String name) {
        return cuisineRepository.findByName(name);
    }

    @GetMapping("/cuisines/contains")
    public List<Cuisine> cuisineContainInsName (@RequestParam("nome")  String name) {
        return cuisineRepository.findByNameContaining(name);
    }
    @GetMapping("/cuisines/starting")
    public List<Cuisine> cuisineStartingBy(@RequestParam("nome")  String name) {
        return cuisineRepository.queryByNameStartingWith(name);
    }

    @GetMapping("/cuisines/ending")
    public List<Cuisine> cuisineEndingBy(@RequestParam("nome")  String name) {
        return cuisineRepository.findByNameEndingWith(name);
    }

    @GetMapping("/restaurants/rangetaxes")
    public List<Restaurant> resturantsByRangeOfFees(@RequestParam BigDecimal initial, @RequestParam BigDecimal end) {
        return restaurantRepository.findBydeliveryFeeBetween(initial, end);
    }

    @GetMapping("/restaurants/rangetaxescustomizado")
    public List<Restaurant> resturantsByRangeOfFeesCustomizada(@RequestParam String name, @RequestParam BigDecimal initial, @RequestParam BigDecimal end) {
        return restaurantRepository.procurarRestauranteNasFaixas(name, initial, end);
    }

    @GetMapping("/restaurants/namepluscuisineid")
    public List<Restaurant> restaurantsByNameAndCuisineId(@RequestParam String name, @RequestParam Long id) {
        return restaurantRepository.findByNameStartingWithAndCuisineId(name, id);
    }

    @GetMapping("/restaurants/querynamepluscuisineid")
    public List<Restaurant> queryRestaurantsByNameAndCuisineId(@RequestParam String nome, @RequestParam Long id) {
        return restaurantRepository.consultarPorNomePorCozinha(nome, id);
    }

    @GetMapping("/restaurants/findfirst")
    public Optional<Restaurant> firstRestaurantByName(@RequestParam String name) {
        return restaurantRepository.findFirstRestaurantByNameContaining(name);
    }

    @GetMapping("/restaurants/findtoptwo")
    public List<Restaurant> firstTopTwoRestaurantsByName(@RequestParam String name) {
        return restaurantRepository.findTop2RestaurantsByNameContaining(name);
    }


}
