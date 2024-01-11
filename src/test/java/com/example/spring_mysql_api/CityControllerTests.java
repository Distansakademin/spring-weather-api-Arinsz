package com.example.spring_mysql_api;
import com.example.spring_mysql_api.entities.City;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import com.example.spring_mysql_api.services.*;
import com.example.spring_mysql_api.controllers.CityController;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.*;

@SpringBootTest
public class CityControllerTests {

    @Mock
    CityService cityService;

    @InjectMocks
    CityController cityController;


    @Test
    public void testCreateCity() {

        //Given
        City expectedCity = new City(1L, "Stockholm");
        when(cityService.createCity(expectedCity, 1L)).thenReturn(new ApiResponse<>(expectedCity, null));

        //When
        ResponseEntity<ApiResponse<City>> responseEntity = cityController.createCity(1L, expectedCity);

        var actualCity = Objects.requireNonNull(responseEntity.getBody()).getData();

        //Then
        assertEquals(expectedCity, actualCity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

    }


    @Test
    public void testGetCityById() {

        City expectedCity = new City(1L, "Stockholm");
        when(cityService.getCityById(1L)).thenReturn(new ApiResponse<>(expectedCity, null));

        ResponseEntity<ApiResponse<City>> responseEntity = cityController.getCityById(1L);

        var actualCity = Objects.requireNonNull(responseEntity.getBody()).getData();

        assertEquals(expectedCity, actualCity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

    }


    @Test
    public void testGetAllCities() {
        // Given
        City city1 = new City(1L, "Stockholm");
        City city2 = new City(2L, "Oslo");
        List<City> expectedCities = Arrays.asList(city1, city2);

        when(cityService.getAllCities()).thenReturn(new ApiResponse<>(expectedCities, null));

        // When
        ResponseEntity<ApiResponse<List<City>>> responseEntity = cityController.getAllCitys();
        List<City> actualCities = Objects.requireNonNull(responseEntity.getBody()).getData();

        // Then
        assertEquals(expectedCities, actualCities);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void updateCity() {

        // Given
        City expectedCity = new City(1L, "Stockholm");
        when(cityService.updateCity(1L, expectedCity)).thenReturn(new ApiResponse<>(expectedCity, null));

        //When
        ResponseEntity<ApiResponse<City>> actualCity = cityController.updateCity(1L, expectedCity);

        assertEquals(expectedCity, Objects.requireNonNull(actualCity.getBody()).getData());
        assertEquals(HttpStatus.OK, actualCity.getStatusCode());
        assertNotNull(actualCity.getBody());
    }


    @Test
    public void testDeleteCityById() {

        // Given
        Long cityId = 1L;
        City city = new City(cityId, "Stockholm");
        when(cityService.deleteCityById(cityId)).thenReturn(new ApiResponse<>(null, null));

        //When
        ResponseEntity<ApiResponse<Void>> actualResponseEntity = cityController.deleteCityById(cityId);


        // Then
        assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode());

    }

}