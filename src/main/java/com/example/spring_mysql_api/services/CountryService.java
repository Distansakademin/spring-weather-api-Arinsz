package com.example.spring_mysql_api.services;
import com.example.spring_mysql_api.repositories.*;
import com.example.spring_mysql_api.entities.*;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository, CityRepository cityRepository) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;

    }


    public ApiResponse<Country> createCountry(Country country) {
        try {

            if (country == null) {
                return new ApiResponse<>(null, "Country creation failed. Input data is null.");
            }

            Country savedCountry = countryRepository.save(country);
            return new ApiResponse<>(savedCountry, null);

        } catch (DataIntegrityViolationException e) {
            return new ApiResponse<>(null, "Country creation failed. Duplicate entry or invalid data.");
        } catch (Exception e) {
            return new ApiResponse<>(null, "Internal server error occurred.");
        }
    }

    public ApiResponse<Country> getCountryById(Long id) {

        try {
            Optional<Country> countryToGet = countryRepository.findById(id);

            return countryToGet.map(country -> new ApiResponse<>(country, null)).orElseGet(()
                    -> new ApiResponse<>(null, "Country not found for id:" + id));

        } catch (Exception e) {
            return new ApiResponse<>(null, "Internal server error occurred.");
        }

    }


    public ApiResponse<List<Country>> getAllCountries() {

        try {
            Iterable<Country> countryIterable = countryRepository.findAll();
            List<Country> countries = new ArrayList<>();
            countryIterable.forEach(countries::add);

            if (!countries.isEmpty()) {
                return new ApiResponse<>(countries, null);
            } else {
                return new ApiResponse<>(null, "No countries found");
            }
        } catch (Exception e) {
            return new ApiResponse<>(null, "Internal server error occurred");
        }

    }

    public ApiResponse<Country> updateCountry(Long id, Country updatedCountry) {
        try {
            return updateCountryIfExists(id, updatedCountry);
        } catch (DataIntegrityViolationException e) {

            return new ApiResponse<>(null, "Country update failed. Duplicate entry or invalid data.");

        } catch (Exception e) {

            return new ApiResponse<>(null, "Internal server error occurred during country update.");
        }
    }

    private ApiResponse<Country> updateCountryIfExists(Long id, Country updatedCountry) {
        ApiResponse<Country> existingCountryResponse = getCountryById(id);

        if (existingCountryResponse.getData() != null) {
            Country existingCountry = existingCountryResponse.getData();

            existingCountry.setCountryName(updatedCountry.getCountryName());
            Country savedCountry = countryRepository.save(existingCountry);

            return new ApiResponse<>(savedCountry, null);
        } else {
            return existingCountryResponse;
        }
    }


    public ApiResponse<Void> deleteCountryById(Long id) {
        try {
            Optional<Country> countryToDelete = countryRepository.findById(id);

            if (countryToDelete.isPresent()) {
                countryRepository.deleteById(id);
                return new ApiResponse<>(null, null);
            } else {
                return new ApiResponse<>(null, "Country not found for deletion");
            }
        } catch (Exception e) {
            return new ApiResponse<>(null, "Internal server error occurred during country deletion");
        }
    }

    public ApiResponse<List<City>> getCitiesInCountry(String countryName) {
        try {
            Country country = countryRepository.findByCountryName(countryName)
                    .orElse(null);

            if (country != null) {
                List<City> cities = cityRepository.findByCountryId(country.getId());
                return new ApiResponse<>(cities, null);
            } else {
                return new ApiResponse<>(null, "Country not found");
            }
        } catch (Exception e) {
            return new ApiResponse<>(null, "Internal server error");
        }
    }
}










