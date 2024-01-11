package com.example.spring_mysql_api.utilitysTests;
import com.example.spring_mysql_api.entities.Country;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApiResponseTests {


    @Test
    public void testApiResponseWithData() {

        // Given
        Country country = new Country(1L, "Sweden");

        // When
        ApiResponse<Country> response = new ApiResponse<>(country, null);

        // Then
        assertEquals(country, response.getData());
        assertNull(response.getErrorMessage());
    }

    @Test
    public void testApiResponseWithList() {

        // Given
        List<Country> countryList = Arrays.asList(
                new Country(1L, "Sweden"),
                new Country(2L, "Norway"));

        // When
        ApiResponse<List<Country>> response = new ApiResponse<>(countryList, null);

        // Then
        assertEquals(countryList, response.getData());
        assertNull(response.getErrorMessage());
    }

    @Test
    public void testApiResponseWithErrorMessage() {

        // Given
        Country country = new Country(1L, "Sweden");

        // When
        ApiResponse<Country> response = new ApiResponse<>(country, "Error message");

        // Then
        assertEquals(country, response.getData());
        assertEquals("Error message", response.getErrorMessage());
    }

    @Test
    public void testApiResponseWithListAndErrorMessage() {

        // Given
        List<Country> countryList = Arrays.asList(
                new Country(1L, "Sweden"),
                new Country(2L, "Norway"));

        // When
        ApiResponse<List<Country>> response = new ApiResponse<>(countryList, "Error message");

        // Then
        assertEquals(countryList, response.getData());
        assertEquals("Error message", response.getErrorMessage());
    }
}

