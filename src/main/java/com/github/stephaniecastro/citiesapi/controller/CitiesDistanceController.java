package com.github.stephaniecastro.citiesapi.controller;


import com.github.stephaniecastro.citiesapi.enums.EarthRadius;
import com.github.stephaniecastro.citiesapi.service.CitiesDistanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distances")
public class CitiesDistanceController {

    private final CitiesDistanceService citiesDistanceService;
    Logger log = LoggerFactory.getLogger(CitiesDistanceController.class);

    public CitiesDistanceController(CitiesDistanceService citiesDistanceService) {
        this.citiesDistanceService = citiesDistanceService;
    }

    //Feito pelo BD. Retorna em milhas.
    @GetMapping("/by-points")
    public Double byPoints(@RequestParam(name = "from") final Long city1,
                           @RequestParam(name = "to") final Long city2) {
        log.info("byPoints");
        return citiesDistanceService.distanceByPointsInMiles(city1, city2);
    }

    //Feito pelo BD. Retorna em metros.
    @GetMapping("/by-cube")
    public Double byCube(@RequestParam(name = "from") final Long city1,
                         @RequestParam(name = "to") final Long city2) {
        log.info("byCube");
        return citiesDistanceService.distanceByCubeInMeters(city1, city2);
    }

    //Cálculo manual
    @GetMapping("/by-math")
    public Double byMath(@RequestParam(name = "from") final Long city1,
                         @RequestParam(name = "to") final Long city2,
                         @RequestParam(name = "unit") final EarthRadius unit) {
        log.info("byMath");
        return citiesDistanceService.distanceUsingMath(city1, city2, unit);
    }
}