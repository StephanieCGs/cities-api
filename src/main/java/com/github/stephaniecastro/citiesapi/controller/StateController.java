package com.github.stephaniecastro.citiesapi.controller;

import com.github.stephaniecastro.citiesapi.entity.State;
import com.github.stephaniecastro.citiesapi.repository.StateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    private final StateRepository stateRepository;

    public StateController(final StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @GetMapping
    public List<State> states(){

        System.out.println("controller state");
        System.out.println(stateRepository.findAll());
        return stateRepository.findAll();
    }

    /*
    @GetMapping
    public Page<State> states(final Pageable page) {
        return stateRepository.findAll(page);
    }
     */

}
