package com.example.spring_mysql_api;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import com.example.spring_mysql_api.entities.*;
import com.example.spring_mysql_api.utilitys.ApiResponse;
import com.example.spring_mysql_api.services.*;
import com.example.spring_mysql_api.repositories.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTests {


    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CountryService countryService;


    @Test
    public void testCreateCountry() {

        // Given
        Country country = new Country(1L, "Norway");

        // When
        countryService.createCountry(country);

        String expected = "Norway";
        String actual = country.getCountryName();

        // Then
        assertNotNull(country.getId());
        assertEquals(expected, actual);

    }

    @Test
    public void testCreateDuplicateCountry() {

        // Given
        Country country = new Country(1L, "Norway");


        // Mock the repository behavior
        when(countryRepository.save(country)).thenThrow(DataIntegrityViolationException.class);

        // When
        ApiResponse<Country> response = countryService.createCountry(country);

        String expected = "Country creation failed. Duplicate entry or invalid data.";
        String actual = response.getErrorMessage();

        // Then
        assertEquals(expected, actual);
        assertNull(response.getData());
        assertEquals(1L, country.getId());

    }

    @Test
    public void testGetCountryById() {

        // Given
        Long countryId = 8L;
        Country expected = new Country(8L, "Sweden");

        // Mock the repository behavior
        when(countryRepository.findById(countryId)).thenReturn(Optional.of(expected));

        // When
        ApiResponse<Country> actual = countryService.getCountryById(countryId);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual.getData());

    }

    @Test
    void testCreateCountryWithNullInput() {

        // Given
        Country nullCountry = null;

        // When
        ApiResponse<Country> response = countryService.createCountry(nullCountry);

        String expected = "Country creation failed. Input data is null.";
        String actual = response.getErrorMessage();

        // Then
        assertNull(response.getData());
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAllCountries() {

        // Given
        Country country1 = new Country(1L, "Sweden");
        Country country2 = new Country(2L, "Norway");
        Country country3 = new Country(3L, "Finland");
        Country country4 = new Country(4L, "Denmark");

        List<Country> expected = Arrays.asList(country1, country2, country3, country4);

        // Mock the repository behavior
        when(countryRepository.findAll()).thenReturn(expected);

        // When
        ApiResponse<List<Country>> actual = countryService.getAllCountries();

        // Then
        assertEquals(expected, actual.getData());


    }

    @Test
    public void testUpdateCountry() {

        // Given
        Long countryId = 1L;
        Country existingCountry = new Country(countryId, "Existing Country");
        Country updatedCountry = new Country(countryId, "Updated Country");

        when(countryRepository.findById(countryId)).thenReturn(Optional.of(existingCountry));
        when(countryRepository.save(existingCountry)).thenReturn(updatedCountry);

        // When
        ApiResponse<Country> result = countryService.updateCountry(countryId, updatedCountry);

        var expected = "Updated Country";
        var actual = updatedCountry.getCountryName();


        // Then
        assertEquals(expected, actual);
        assertEquals(updatedCountry, result.getData());
        assertNull(result.getErrorMessage());


    }

    @Test
    public void testDeleteCountryById() {


        // Given
        Long countryIdToDelete = 1L;
        Country countryToDelete = new Country(countryIdToDelete, "Country to Delete");

        // Mock the repository behavior
        when(countryRepository.findById(countryIdToDelete)).thenReturn(Optional.of(countryToDelete));

        // When
        ApiResponse<Void> result = countryService.deleteCountryById(countryIdToDelete);

        // Then
        verify(countryRepository, times(1)).deleteById(countryIdToDelete);


        assertNull(result.getData());
        assertNull(result.getErrorMessage());
    }

    @Test
    public void testGetCitiesInCountry() {

        // Given
        String countryName = "Sweden";
        Country country = new Country(1L, countryName);
        List<City> cities = Arrays.asList(new City(1L, "Stockholm"), new City(2L, "Malmo"));

        // Mock the repository behavior
        when(countryRepository.findByCountryName(countryName)).thenReturn(Optional.of(country));
        when(cityRepository.findByCountryId(country.getId())).thenReturn(cities);

        // When
        ApiResponse<List<City>> actual = countryService.getCitiesInCountry(countryName);

        // Then
        assertNotNull(actual);
        assertEquals(cities, actual.getData());
        assertNull(actual.getErrorMessage());

    }

}




