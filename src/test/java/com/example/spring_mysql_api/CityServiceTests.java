package com.example.spring_mysql_api;
import com.example.spring_mysql_api.entities.*;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import com.example.spring_mysql_api.services.*;
import com.example.spring_mysql_api.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CityServiceTests {


    @Mock
    CityRepository cityRepository;

    @Mock
    CountryRepository countryRepository;


    @InjectMocks
    CityService cityService;


    @Test
    public void testCreateCity() {
        // Given
        Country country = new Country(1L, "Sweden"); // Create a Country object
        City city = new City(1L, "Stockholm", country); // Associate the City with the Country

        // When
        when(countryRepository.findById(1L)).thenReturn(Optional.of(country)); // Mock the countryRepository
        when(cityRepository.save(any(City.class))).thenReturn(city);
        ApiResponse<City> response = cityService.createCity(city, 1L);

        // Then
        var expectedCityName = "Stockholm";
        var actualCityName = response.getData().getCityName();

        assertEquals(expectedCityName, actualCityName);
        assertNotNull(response.getData());
        assertNull(response.getErrorMessage());
    }


    @Test
    public void testGetNonExistingCityById() {

        // Given
        Long cityId = 1L;

        // When
        when(cityRepository.findById(cityId)).thenReturn(Optional.empty());
        ApiResponse<City> response = cityService.getCityById(cityId);

        // Then
        var expected = "City not found for id:" + cityId;
        var actual = response.getErrorMessage();

        assertEquals(expected, actual);
        assertNull(response.getData());
    }


    @Test
    public void testGetExistingCityById() {

        //Given
        Long cityId = 1L;
        City city = new City(cityId, "Stockholm");

        //When
        when(cityRepository.findById(cityId)).thenReturn(Optional.of(city));
        ApiResponse<City> response = cityService.getCityById(cityId);

        //Then
        var expected = "Stockholm";
        var actual = response.getData().getCityName();

        assertEquals(expected, actual);
        assertNotNull(response.getData());
        assertNull(response.getErrorMessage());


    }

    @Test
    public void testGetAllCities() {

        // Given
        List<City> cityList = new ArrayList<>();
        cityList.add(new City(1L, "Stockholm"));
        cityList.add(new City(2L, "Stockholm2"));

        // When
        when(cityRepository.findAll()).thenReturn(cityList);
        ApiResponse<List<City>> response = cityService.getAllCities();

        // Then
        assertNull(response.getErrorMessage());
        assertNotNull(response.getData());

        var expectedCity1 = "Stockholm";
        var expectedCity2 = "Stockholm2";
        var actualCity1 = response.getData().get(0).getCityName();
        var actualCity2 = response.getData().get(1).getCityName();

        assertEquals(expectedCity1, actualCity1);
        assertEquals(expectedCity2, actualCity2);
        assertEquals(2, response.getData().size());
    }


    @Test
    public void testUpdateCity() {

        // Given
        Long cityId = 1L;
        City existingCity = new City(cityId, "Stockholm");
        City updatedCity = new City(cityId, "Stockholm2");

        // When
        when(cityRepository.findById(cityId)).thenReturn(Optional.of(existingCity));
        when(cityRepository.save(any(City.class))).thenReturn(updatedCity);
        ApiResponse<City> response = cityService.updateCity(cityId, updatedCity);

        // Then
        var expected = "Stockholm2";
        var actual = response.getData().getCityName();

        assertEquals(expected, actual);
        assertNotNull(response.getData());
        assertNull(response.getErrorMessage());

    }

    @Test
    public void testDeleteCityById() {

        // Given
        Long cityId = 1L;
        City city = new City(cityId, "Stockholm");

        // When
        when(cityRepository.findById(cityId)).thenReturn(Optional.of(city));
        ApiResponse<Void> response = cityService.deleteCityById(cityId);

        // Then
        assertNull(response.getData());
        assertNull(response.getErrorMessage());

    }


}