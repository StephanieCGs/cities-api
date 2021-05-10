package com.github.stephaniecastro.citiesapi.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "City")
@Table(name = "cidade")
public class City {

    @Id
    private Long id;

    @Column(name = "nome")
    private String name;

    private Integer uf;

    private Integer ibge;

    private Double latitude;

    private Double longitude;

    private String lat_lon;


    private int cod_tom;


    public City() {
    }


    public City(final Long id, final String name, final Integer uf, final Integer ibge,
                final String latitude, final String longitude, final String lat_lon, final Integer cod_tom) {
        this.id = id;
        this.name = name;
        this.uf = uf;
        this.ibge = ibge;
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.lat_lon = lat_lon;
        this.cod_tom = cod_tom;
    }




    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getUf() {
        return uf;
    }

    public Integer getIbge() {
        return ibge;
    }


    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getLat_lon() {
        return lat_lon;
    }

    public Integer getCod_tom() {
        return cod_tom;
    }


}
