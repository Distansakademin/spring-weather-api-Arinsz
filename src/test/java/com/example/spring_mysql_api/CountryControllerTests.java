package com.example.spring_mysql_api;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import com.example.spring_mysql_api.entities.*;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import com.example.spring_mysql_api.services.*;
import com.example.spring_mysql_api.controllers.*;
import org.springframework.http.ResponseEntity;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class CountryControllerTests {

    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;


    @Test
    public void testCreateCountry() {

        //Given
        Country testCountry = new Country();
        when(countryService.createCountry(testCountry)).thenReturn(new ApiResponse<>(testCountry, null));

        //When
        ResponseEntity<ApiResponse<Country>> responseEntity = countryController.createCountry(testCountry);

        //Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(testCountry, responseEntity.getBody().getData());

    }

    @Test
    public void testGetCountryById() {

        // Given
        Long testCountryId = 1L;
        Country testCountry = new Country();
        when(countryService.getCountryById(testCountryId)).thenReturn(new ApiResponse<>(testCountry, null));

        // When
        ResponseEntity<ApiResponse<Country>> responseEntity = countryController.getCountryById(testCountryId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(testCountry, responseEntity.getBody().getData());

    }

    @Test
    public void testGetAllCountries() {

        // Given
        List<Country> testCountries = Arrays.asList(new Country(), new Country()); // Create test countries
        when(countryService.getAllCountries()).thenReturn(new ApiResponse<>(testCountries, null));

        // When
        ResponseEntity<ApiResponse<List<Country>>> responseEntity = countryController.getAllCountries();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(testCountries, responseEntity.getBody().getData());
        verify(countryService, times(1)).getAllCountries();
    }

    @Test
    public void testUpdateCountry() {

        // Given
        Long countryId = 1L;
        String updatedCountryName = "UpdatedCountryName";
        Country updatedCountry = new Country(countryId, updatedCountryName);

        // Mock the service method to return the updated country
        when(countryService.updateCountry(eq(countryId), any(Country.class)))
                .thenReturn(new ApiResponse<>(updatedCountry, null));

        // When
        ResponseEntity<ApiResponse<Country>> updateResponseEntity = countryController.updateCountry
                (countryId, new Country());

        var actualCountryName = Objects.requireNonNull(updateResponseEntity.getBody()).getData().getCountryName();

        // Then
        assertEquals(HttpStatus.OK, updateResponseEntity.getStatusCode());
        assertNotNull(updateResponseEntity.getBody());
        assertEquals(updatedCountryName, actualCountryName);
        verify(countryService, times(1)).updateCountry(eq(countryId), any(Country.class));
    }


    @Test
    void testDeleteCountryById() {
        // Given
        Long countryId = 1L;

        // Mock the service method to return a successful ApiResponse<Void>
        when(countryService.deleteCountryById(countryId)).thenReturn(new ApiResponse<>(null, null));

        // When
        ResponseEntity<ApiResponse<Void>> responseEntity = countryController.deleteCountryById(countryId);

        // Then
        assertNotNull(responseEntity.getBody());
        assertNull(responseEntity.getBody().getData());
        assertNull(responseEntity.getBody().getErrorMessage());


    }

    @Test
    void testGetCitiesInCountry() {

        // Given
        String testCountryName = "Sweden";
        List<City> testCities = Arrays.asList(new City(1L, "Stockholm"), new City(2L, "Malmo"));
        when(countryService.getCitiesInCountry(testCountryName)).thenReturn(new ApiResponse<>(testCities, null));

        // When
        ResponseEntity<ApiResponse<List<City>>> responseEntity = countryController.getCitiesInCountry(testCountryName);

        // Then
        var actual = Objects.requireNonNull(responseEntity.getBody()).getData();

        assertEquals(testCities, actual);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

    }
}




