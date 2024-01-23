package com.example.spring_mysql_api.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class City {


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id")
    private Country country;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;




    @NotBlank
    @Column(unique = true)
    private String cityName;



    public City() {
    }

    public City(Long id, String cityName) {
        this.id = id;
        this.cityName = cityName;


    }

    public City(Long id, String cityName, Country country) {
        this.id = id;
        this.cityName = cityName;
        this.country = country;

    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountry(Country country) {
        this.country = country;
    }



    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public Country getCountry() {
        return country;
    }

}
