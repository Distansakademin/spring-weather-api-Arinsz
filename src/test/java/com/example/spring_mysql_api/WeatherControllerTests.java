package com.example.spring_mysql_api;
import com.example.spring_mysql_api.entities.*;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import com.example.spring_mysql_api.services.*;
import com.example.spring_mysql_api.controllers.WeatherController;
import com.example.spring_mysql_api.utilitys.Weather;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherControllerTests {

    @Mock
    private WeatherService weatherService;


    @InjectMocks
    private WeatherController weatherController;


    @Test
    public void testGetWeatherByCityId() {

        // Given
        Long cityId = 1L;
        City city = new City(cityId, "Stockholm");
        when(weatherService.getWeatherByCityId(cityId)).thenReturn
                (new ApiResponse<>(new Weather("Sunny", 25), null));

        // When
        ApiResponse<Weather> response = weatherService.getWeatherByCityId(cityId);
        Weather weather = response.getData();

        // Then
        var expected = "Sunny";
        var actual = weather.getCondition();
        var expectedTemp = 25;
        var actualTemp = weather.getTemperature();

        assertEquals(expected, actual);
        assertEquals(expectedTemp, actualTemp);
        assertNull(response.getErrorMessage());

    }
}



