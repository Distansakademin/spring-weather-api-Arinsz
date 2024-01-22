package com.example.spring_mysql_api;
import com.example.spring_mysql_api.entities.*;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import com.example.spring_mysql_api.services.*;
import com.example.spring_mysql_api.controllers.WeatherController;
import com.example.spring_mysql_api.utilitys.Weather;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerTests {

    @MockBean
    private WeatherService weatherService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetWeatherByCityId() throws Exception {
        // Given
        Long testCityId = 1L;
        Weather testWeather = new Weather("Sunny", 30);

        // Mock the service response for getWeatherByCityId
        when(weatherService.getWeatherByCityId(testCityId)).thenReturn(new ApiResponse<>(testWeather, null));

        // When
        ResultActions resultActions = mockMvc.perform(get("/api/weather/{city_id}", testCityId));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.condition").value("Sunny"))
                .andExpect(jsonPath("$.data.temperature").value("30"));
    }
}
