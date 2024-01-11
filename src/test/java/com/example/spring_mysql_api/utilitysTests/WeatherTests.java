package com.example.spring_mysql_api.utilitysTests;
import com.example.spring_mysql_api.utilitys.Weather;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class WeatherTests {


    @Test
    public void testWeatherInitialization() {

        Weather weather = new Weather("Sunny", 25);
        assertEquals("Sunny", weather.getCondition());
        assertEquals(25, weather.getTemperature());
    }

    @Test
    public void testSetCondition() {

        Weather weather = new Weather();
        weather.setCondition("Rainy");
        assertEquals("Rainy", weather.getCondition());
    }


    @Test
    public void testSetTemperature() {

        Weather weather = new Weather();
        weather.setTemperature(15);
        assertEquals(15, weather.getTemperature());
    }


}
