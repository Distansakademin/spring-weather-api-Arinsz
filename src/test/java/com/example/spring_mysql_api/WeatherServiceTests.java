package com.example.spring_mysql_api;
import com.example.spring_mysql_api.entities.*;
import com.example.spring_mysql_api.services.*;
import com.example.spring_mysql_api.repositories.*;
import com.example.spring_mysql_api.utilitys.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTests {


    @Mock
    CityRepository cityRepository;

    @InjectMocks
    WeatherService weatherService;


    @Test
    public void testGetWeatherByCityId() {

        // Given
        Long cityId = 1L;
        City city = new City(cityId, "Stockholm");
        when(cityRepository.findById(cityId)).thenReturn(Optional.of(city));

        // When
        ApiResponse<Weather> response = weatherService.getWeatherByCityId(cityId);
        Weather weather = response.getData();

        // Then
        assertNotNull(response);
        assertNotNull(weather);
        assertNull(response.getErrorMessage());

    }

    @Test
    public void testGenerateFakeWeather() {
        // When
        ApiResponse<Weather> response = weatherService.generateFakeWeather();
        Weather fakeWeather = response.getData();

        // Then
        assertNotNull(response);
        assertNotNull(fakeWeather);
        assertNull(response.getErrorMessage());
    }
}

