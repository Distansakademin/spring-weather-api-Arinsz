package com.example.spring_mysql_api;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import com.example.spring_mysql_api.entities.*;
import com.example.spring_mysql_api.repositories.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest

public class SpringBootTests {


    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void testCrudOperations() {

        // Create
        Country country = new Country();
        country.setId(98L);
        country.setCountryName("Sweden");

        // Save
        Country savedCountry = countryRepository.save(country);
        assertNotNull(savedCountry.getId(), "Country ID should not be null after saving");
        assertEquals("Sweden", savedCountry.getCountryName(), "Country name should match");

        // Read (one Country)
        Country retrievedCountry = countryRepository.findById(savedCountry.getId()).orElse(null);
        assertNotNull(retrievedCountry, "Retrieved country should not be null");
        assertEquals(savedCountry.getId(), retrievedCountry.getId(), "ID should match");

        // Read (all Countries)
        Iterable<Country> iterableCountries = countryRepository.findAll();
        List<Country> allCountries = new ArrayList<>();
        iterableCountries.forEach(allCountries::add);

        assertTrue(allCountries.size() > 0, "There should be at least one country in the database");

        // Update
        retrievedCountry.setCountryName("UpdatedSweden");
        Country updatedCountry = countryRepository.save(retrievedCountry);
        assertEquals("UpdatedSweden", updatedCountry.getCountryName(), "Country name should be updated");

        // Delete
        countryRepository.deleteById(updatedCountry.getId());
        assertFalse(countryRepository.existsById(updatedCountry.getId()), "Country should be deleted");
    }
}





