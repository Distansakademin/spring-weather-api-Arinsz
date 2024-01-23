package com.example.spring_mysql_api;

import com.example.spring_mysql_api.entities.City;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import com.example.spring_mysql_api.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerTests {

    @MockBean
    private CityService cityService;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateCity() throws Exception {
        // Given
        Long testCountryId = 1L;
        City testCity = new City(1L, "Halmstad");

        // Mock the service response
        when(cityService.createCity(any(), eq(testCountryId))).thenReturn(new ApiResponse<>(testCity, null));

        // When
        ResultActions resultActions = mockMvc.perform(post("/api/cities/create/{countryId}", testCountryId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testCity)));

        // Then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.cityName").value("Halmstad"));
    }

    @Test
    public void testGetCityById() throws Exception {
        // Given
        Long testCityId = 1L;
        City testCity = new City(testCityId, "Halmstad");

        // Mock the service response
        when(cityService.getCityById(testCityId)).thenReturn(new ApiResponse<>(testCity, null));

        // When
        ResultActions resultActions = mockMvc.perform(get("/api/cities/{id}", testCityId));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.cityName").value("Halmstad"));
    }

    @Test
    public void testGetAllCities() throws Exception {
        // Given
        List<City> testCities = Arrays.asList(new City(1L, "Halmstad1"), new City(2L, "Halmstad2"));

        // Mock the service response
        when(cityService.getAllCities()).thenReturn(new ApiResponse<>(testCities, null));

        // When
        ResultActions resultActions = mockMvc.perform(get("/api/cities/getAll"));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].cityName").value("Halmstad1"))
                .andExpect(jsonPath("$.data[1].cityName").value("Halmstad2"));
    }

    @Test
    public void testUpdateCity() throws Exception {


        // Given
        Long testCityId = 1L;
        City testCity = new City(testCityId, "Halmstad1");
        City updatedCity = new City(testCityId, "Halmstad2");

        // Mock the service response
        when(cityService.updateCity(eq(testCityId), any())).thenReturn(new ApiResponse<>(updatedCity, null));

        // When
        ResultActions resultActions = mockMvc.perform(put("/api/cities/{id}", testCityId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCity)));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.cityName").value("Halmstad2"));


    }

    @Test
    public void testDeleteCityById() throws Exception {


        // Given
        Long testCityId = 1L;

        // Mock the service response
        when(cityService.deleteCityById(testCityId)).thenReturn(new ApiResponse<>(null, null));

        // When
        ResultActions resultActions = mockMvc.perform(delete("/api/cities/{id}", testCityId));

        // Then
        resultActions.andExpect(status().isOk());


    }
}

