package com.github.stephaniecastro.citiesapi.repository;

import com.github.stephaniecastro.citiesapi.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
