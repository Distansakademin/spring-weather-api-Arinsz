package com.example.spring_mysql_api.controllers;
import com.example.spring_mysql_api.entities.City;
import com.example.spring_mysql_api.entities.Country;
import com.example.spring_mysql_api.services.CountryService;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/countries/create")
    public ResponseEntity<ApiResponse<Country>> createCountry(@RequestBody Country country) {

        ApiResponse<Country> response = countryService.createCountry(country);

        if (response.getData() != null) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<ApiResponse<Country>> getCountryById(@PathVariable Long id) {

        ApiResponse<Country> response = countryService.getCountryById(id);

        if (response.getData() != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/countries/getAll")
    public ResponseEntity<ApiResponse<List<Country>>> getAllCountries() {
        ApiResponse<List<Country>> response = countryService.getAllCountries();

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/countries/{id}")
    public ResponseEntity<ApiResponse<Country>> updateCountry(@PathVariable Long id, @RequestBody Country country) {
        ApiResponse<Country> response = countryService.updateCountry(id, country);

        if (response.getData() != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/countries/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCountryById(@PathVariable Long id) {
        ApiResponse<Void> response = countryService.deleteCountryById(id);
        HttpStatus httpStatus;

        if (response.getErrorMessage() == null) {

            httpStatus = HttpStatus.OK;

        } else {

            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/{country}/cities")
    public ResponseEntity<ApiResponse<List<City>>> getCitiesInCountry(@PathVariable String country) {
        ApiResponse<List<City>> response = countryService.getCitiesInCountry(country);

        if (response.getData() != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}








