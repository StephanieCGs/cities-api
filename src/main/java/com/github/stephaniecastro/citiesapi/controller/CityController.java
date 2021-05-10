package com.github.stephaniecastro.citiesapi.controller;

import com.github.stephaniecastro.citiesapi.entity.City;
import com.github.stephaniecastro.citiesapi.repository.CityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityRepository cityRepository;

    public CityController(final CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping
    public List<City> cities(){

        System.out.println("controller city");
        System.out.println(cityRepository.findAll());
        return cityRepository.findAll();
    }

    /*
    @GetMapping
    public Page<City> cities(final Pageable page) {
        return cityRepository.findAll(page);
    }
     */
}
