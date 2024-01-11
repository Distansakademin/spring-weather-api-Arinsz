package com.example.spring_mysql_api.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.spring_mysql_api.entities.*;
import com.example.spring_mysql_api.services.*;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {

    private CityService cityService;


    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/cities/create/{countryId}")
    public ResponseEntity<ApiResponse<City>> createCity(@PathVariable Long countryId, @RequestBody City city) {

        ApiResponse<City> response = cityService.createCity(city, countryId);

        if (response.getData() != null) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<ApiResponse<City>> getCityById(@PathVariable Long id) {
        ApiResponse<City> response = cityService.getCityById(id);

        if (response.getData() != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cities/getAll")
    public ResponseEntity<ApiResponse<List<City>>> getAllCitys() {
        ApiResponse<List<City>> response = cityService.getAllCities();

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<ApiResponse<City>> updateCity(@PathVariable Long id, @RequestBody City city) {
        ApiResponse<City> response = cityService.updateCity(id, city);

        if (response.getData() != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCityById(@PathVariable Long id) {
        ApiResponse<Void> response = cityService.deleteCityById(id);
        HttpStatus httpStatus;

        if (response.getErrorMessage() == null) {
            httpStatus = HttpStatus.OK;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, httpStatus);
    }
}



