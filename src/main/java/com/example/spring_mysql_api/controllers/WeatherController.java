package com.example.spring_mysql_api.controllers;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import com.example.spring_mysql_api.utilitys.Weather;
import com.example.spring_mysql_api.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("{city_id}")
    public ResponseEntity<ApiResponse<Weather>> getWeatherByCityId(@PathVariable Long city_id) {
        ApiResponse<Weather> response = weatherService.getWeatherByCityId(city_id);

        if (response.getData() != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}




