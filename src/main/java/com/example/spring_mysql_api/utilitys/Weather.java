package com.example.spring_mysql_api.utilitys;
import com.example.spring_mysql_api.entities.City;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


public class Weather {

    private String condition;
    private int temperature;


    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Weather() {
    }

    public Weather(String condition, int temperature) {
        this.condition = condition;
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}

