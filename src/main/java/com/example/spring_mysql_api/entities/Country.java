package com.example.spring_mysql_api.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Country {




    @Column(unique = true)
    private String countryName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<City> cities;


    public Country() {
    }


    public Country(Long id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }




    public List<City> getCities() {
        return cities;
    }


    public String getCountryName() {
        return countryName;
    }


    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
}

