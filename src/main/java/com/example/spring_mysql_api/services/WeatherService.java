package com.example.spring_mysql_api.services;
import com.example.spring_mysql_api.repositories.*;
import com.example.spring_mysql_api.entities.*;
import com.example.spring_mysql_api.utilitys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
public class WeatherService {

    private final CityRepository cityRepository;

    @Autowired
    public WeatherService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public ApiResponse<Weather> getWeatherByCityId(Long cityId) {
        try {
            Optional<City> cityOptional = cityRepository.findById(cityId);

            if (cityOptional.isPresent()) {
                ApiResponse<Weather> fakeWeatherResponse = generateFakeWeather();
                Weather fakeWeather = fakeWeatherResponse.getData();

                return new ApiResponse<>(fakeWeather, null);
            } else {
                return new ApiResponse<>(null, "City not found for id:" + cityId);
            }
        } catch (Exception e) {
            return new ApiResponse<>(null, "Internal server error occurred.");
        }
    }

    public ApiResponse<Weather> generateFakeWeather() {

        try {
            String[] conditions = {"Sunny", "Cloudy", "Rainy", "Snowy"};
            String randomCondition = conditions[new Random().nextInt(conditions.length)];

            int randomTemperature = new Random().nextInt(51) - 10;

            Weather fakeWeather = new Weather(randomCondition, randomTemperature);
            return new ApiResponse<>(fakeWeather, null);

        } catch (Exception e) {
            return new ApiResponse<>(null, "Internal server error occurred.");
        }
    }
}
