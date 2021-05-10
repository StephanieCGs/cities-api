package com.github.stephaniecastro.citiesapi.service;


import com.github.stephaniecastro.citiesapi.entity.City;
import com.github.stephaniecastro.citiesapi.enums.EarthRadius;
import com.github.stephaniecastro.citiesapi.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;

@Service
public class CitiesDistanceService {

    private final CityRepository cityRepository;
    Logger log = LoggerFactory.getLogger(CitiesDistanceService.class);

    public CitiesDistanceService(final CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * 1st option
     *
     * @param city1 Long
     * @param city2 Long
     * @param unit EarthRadius
     * @return Double
     */
    public Double distanceUsingMath(final Long city1, final Long city2, final EarthRadius unit) {
        log.info("distanceUsingMath({}, {}, {})", city1, city2, unit);
        final List<City> cities = cityRepository.findAllById((Arrays.asList(city1, city2)));

        Double latitudeCity1 = cities.get(0).getLatitude();
        Double longitudeCity1 = cities.get(0).getLongitude();

        Double latitudeCity2 = cities.get(1).getLatitude();
        Double longitudeCity2 = cities.get(1).getLongitude();

        return doCalculation(latitudeCity1, longitudeCity1, latitudeCity2, longitudeCity2, unit);
    }

    /**
     * 2nd option
     *
     * @param city1
     * @param city2
     * @return
     */
    public Double distanceByPointsInMiles(final Long city1, final Long city2) {
        log.info("nativePostgresInMiles({}, {})", city1, city2);
        return cityRepository.distanceByPoints(city1, city2);
    }

    /**
     * 3rd option
     *
     * @param city1
     * @param city2
     * @param unit
     * @return
     */
    public Double distanceUsingPoints(final Long city1, final Long city2, final EarthRadius unit) {
        log.info("distanceUsingPoints({}, {}, {})", city1, city2, unit);
        final List<City> cities = cityRepository.findAllById((Arrays.asList(city1, city2)));

        Double latitudeCity1 = cities.get(0).getLatitude();
        Double longitudeCity1 = cities.get(0).getLongitude();

        Double latitudeCity2 = cities.get(1).getLatitude();
        Double longitudeCity2 = cities.get(1).getLongitude();

        return doCalculation(latitudeCity1, longitudeCity1, latitudeCity2, longitudeCity2, unit);
    }

    /**
     * 4th option
     *
     * @param city1
     * @param city2
     * @return
     */
    public Double distanceByCubeInMeters(Long city1, Long city2) {
        log.info("distanceByCubeInMeters({}, {})", city1, city2);
        final List<City> cities = cityRepository.findAllById((Arrays.asList(city1, city2)));

        Double latitudeCity1 = cities.get(0).getLatitude();
        Double longitudeCity1 = cities.get(0).getLongitude();

        Double latitudeCity2 = cities.get(1).getLatitude();
        Double longitudeCity2 = cities.get(1).getLongitude();

        return cityRepository.distanceByCube(latitudeCity1, longitudeCity1, latitudeCity2, longitudeCity2);
    }

    private double doCalculation(final double lat1, final double lon1, final double lat2,
                                 final double lng2, final EarthRadius earthRadius) {
        double lat = toRadians(lat2 - lat1);
        double lon = toRadians(lng2 - lon1);
        double a = sin(lat / 2) * sin(lat / 2)
                + cos(toRadians(lat1)) * cos(toRadians(lat2)) * sin(lon / 2) * sin(lon / 2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));

        return earthRadius.getValue() * c;
    }

    /**
     * Serviço de proximidade por raio
     * @param cityId
     * @param radius
     * @return
     */
    public List<City> nearby(Long cityId, Double radius) {
        Optional<City> city = cityRepository.findById(cityId);

        if (city.isPresent()) {
            Double latitude = city.get().getLatitude();
            Double longitude = city.get().getLongitude();

            return cityRepository.citiesByRadius(latitude, longitude, radius);
        }
        return Collections.emptyList();
    }
}