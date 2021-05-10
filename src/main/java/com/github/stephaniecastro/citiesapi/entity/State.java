package com.github.stephaniecastro.citiesapi.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;

@Entity(name = "State")
@Table(name = "estado")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class) // Da dependência implementation 'com.vladmihalcea:hibernate-types-52:2.9.8' que será usada no DDD para poder transformar o JSON que chega em Lista
})
public class State {

    @Id
    private Long id;

    @Column(name = "nome")
    private String name;

    private String uf;

    private Integer ibge;

    /* 1st
    @Column(name = "pais")
    private Integer countryId;*/

    // 2nd - @ManyToOne - Um estado tem um país, e um país tem muitos estados
    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "id")
    private Country country;

    @Type(type = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ddd", columnDefinition = "jsonb")
    private List<Integer> ddd; //DDD é uma lista de inteiros (que representam o DDD) e que chega no formato JSON

    public State() {
    }

    public State(Long id, String name, String uf, Integer ibge,
                 Country country, List<Integer> ddd) {
        this.id = id;
        this.name = name;
        this.uf = uf;
        this.ibge = ibge;
        this.country = country;
        this.ddd = ddd;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUf() {
        return uf;
    }

    public Integer getIbge() {
        return ibge;
    }

    public List<Integer> getDdd() {
        return ddd;
    }

    public Country getCountry() {
        return country;
    }

  /*public Integer getCountryId() {
      return countryId;
  }*/
}
