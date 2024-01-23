package com.example.spring_mysql_api;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.spring_mysql_api.entities.*;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import com.example.spring_mysql_api.services.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTests {


    @MockBean
    private CountryService countryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testCreateCountry() throws Exception {

        // Given
        Country testCountry = new Country(1L, "Sweden");

        // Mock the service response
        when(countryService.createCountry(any())).thenReturn(new ApiResponse<>(testCountry, null));

        // When
        ResultActions resultActions = mockMvc.perform(post("/api/countries/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testCountry)));

        // Then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.countryName").value("Sweden"));

    }


    @Test
    public void testGetCountryById() throws Exception {
        // Given
        Long testCountryId = 1L;
        Country testCountry = new Country(1L, "Sweden");
        when(countryService.getCountryById(testCountryId)).thenReturn(new ApiResponse<>(testCountry, null));

        // When
        ResultActions resultActions = mockMvc.perform(get("/api/countries/{id}", testCountryId));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.countryName").value("Sweden"));
    }

    @Test
    public void testGetAllCountries() throws Exception {
        // Given
        List<Country> testCountries = Arrays.asList(new Country(1L, "Country1"), new Country(2L, "Country2"));
        when(countryService.getAllCountries()).thenReturn(new ApiResponse<>(testCountries, null));

        // When
        ResultActions resultActions = mockMvc.perform(get("/api/countries/getAll"));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].countryName").value("Country1"))
                .andExpect(jsonPath("$.data[1].countryName").value("Country2"));
    }

    @Test
    public void testUpdateCountry() throws Exception {

        //Given
        Long testCountryId = 1L;
        Country testCountry = new Country(testCountryId, "Sweden");
        Country updatedCountry = new Country(testCountryId, "NewCountryName");


        // Mock the service response for updateCountry
        when(countryService.updateCountry(eq(testCountryId), any())).thenReturn(new ApiResponse<>(updatedCountry, null));


        //When
        ResultActions resultActions = mockMvc.perform(put("/api/countries/{id}",
                testCountryId).contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(updatedCountry)));

        //Then
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.data.countryName").value("NewCountryName"));

    }

    @Test
    public void testDeleteCountryById() throws Exception {
        // Given
        Long testCountryId = 1L;

        // Mock the service response for deleteCountryById
        when(countryService.deleteCountryById(testCountryId)).thenReturn(new ApiResponse<>(null, null));

        // When
        ResultActions resultActions = mockMvc.perform(delete("/api/countries/{id}", testCountryId));

        // Then
        resultActions.andExpect(status().isOk());

    }

    @Test
    public void testGetCitiesInCountry() throws Exception {

        // Given
        String testCountryName = "Sweden";
        List<City> testCities = Arrays.asList(new City(1L, "Stockholm"), new City(2L, "Malmö"));

        // Mock the service response for getCitiesInCountry
        when(countryService.getCitiesInCountry(eq(testCountryName))).thenReturn(new ApiResponse<>(testCities, null));

        // When
        ResultActions resultActions = mockMvc.perform(get("/api/{country}/cities", testCountryName));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].cityName").value("Stockholm"))
                .andExpect(jsonPath("$.data[1].cityName").value("Malmö"));

    }

}