package com.github.stephaniecastro.citiesapi.controller;

import com.github.stephaniecastro.citiesapi.entity.Country;
import com.github.stephaniecastro.citiesapi.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    /*
    @Autowired ou o Construtor abaixo
    ******

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    */

    @GetMapping
    public List<Country> countries(){
        return countryRepository.findAll();
    }

    /*
    @GetMapping("/{id}")
    public Country getCountry(@PathVariable Long id){
        Optional<Country> optional = countryRepository.findById(id);

        return optional.get();
    }
    */

    /*
    // http://localhost:8080/countries?page=0&size=10&sort=name,asc
    @GetMapping
    public Page<Country> countries(Pageable page){
        return countryRepository.findAll(page);
    }
     */


    @GetMapping("/{id}")
    public ResponseEntity getCountry(@PathVariable Long id){
        Optional<Country> optional = countryRepository.findById(id);

        if(optional.isPresent()){
            return ResponseEntity.ok().body(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
