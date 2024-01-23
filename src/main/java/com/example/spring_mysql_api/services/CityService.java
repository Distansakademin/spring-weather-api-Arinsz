package com.example.spring_mysql_api.services;
import com.example.spring_mysql_api.repositories.*;
import com.example.spring_mysql_api.entities.*;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public ApiResponse<City> createCity(City city, Long countryId) {
        try {

            if (StringUtils.isBlank(city.getCityName())) {
                return new ApiResponse<>(null, "City creation failed. Name cannot be blank.");
            }

            Optional<Country> countryOptional = countryRepository.findById(countryId);

            if (countryOptional.isPresent()) {

                city.setCountry(countryOptional.get());
                City savedCity = cityRepository.save(city);


                return new ApiResponse<>(savedCity, null);
            } else {
                return new ApiResponse<>(null, "Country not found for id: " + countryId);
            }
        } catch (DataIntegrityViolationException e) {
            return new ApiResponse<>(null, "City creation failed. Duplicate entry or invalid data.");
        } catch (Exception e) {
            return new ApiResponse<>(null, "Internal server error occurred.");
        }
    }

    public ApiResponse<City> getCityById(Long id) {

        try {
            Optional<City> cityToGet = cityRepository.findById(id);
            return cityToGet.map(city -> new ApiResponse<>(city, null)).orElseGet(()
                    -> new ApiResponse<>(null, "City not found for id:" + id));
        } catch (Exception e) {
            return new ApiResponse<>(null, "Internal server error occurred.");
        }
    }


    public ApiResponse<List<City>> getAllCities() {

        try {
            Iterable<City> cityIterable = cityRepository.findAll();
            List<City> citys = new ArrayList<>();
            cityIterable.forEach(citys::add);

            if (!citys.isEmpty()) {
                return new ApiResponse<>(citys, null);
            } else {
                return new ApiResponse<>(null, "No City's found");
            }
        } catch (Exception e) {
            return new ApiResponse<>(null, "Internal server error occurred");
        }

    }

    public ApiResponse<City> updateCity(Long id, City updatedCity) {

        if (StringUtils.isBlank(updatedCity.getCityName())) {
            return new ApiResponse<>(null, "City update failed. Name cannot be blank.");
        }

        try {
            return updateCityIfExists(id, updatedCity);
        } catch (DataIntegrityViolationException e) {

            return new ApiResponse<>(null, "Country update failed. Duplicate entry or invalid data.");

        } catch (Exception e) {

            return new ApiResponse<>(null, "Internal server error occurred during country update.");
        }
    }

    private ApiResponse<City> updateCityIfExists(Long id, City updatedCity) {
        ApiResponse<City> existingCityResponse = getCityById(id);

        if (existingCityResponse.getData() != null) {
            City existingCity = existingCityResponse.getData();

            existingCity.setCityName(updatedCity.getCityName());
            City savedCity = cityRepository.save(existingCity);

            return new ApiResponse<>(savedCity, null);
        } else {
            return existingCityResponse;
        }
    }


    public ApiResponse<Void> deleteCityById(Long id) {
        try {
            Optional<City> cityToDelete = cityRepository.findById(id);

            if (cityToDelete.isPresent()) {
                cityRepository.deleteById(id);
                return new ApiResponse<>(null, null);
            } else {
                return new ApiResponse<>(null, "City not found for deletion");
            }
        } catch (Exception e) {
            return new ApiResponse<>(null, "Internal server error occurred during city deletion");
        }
    }
}



