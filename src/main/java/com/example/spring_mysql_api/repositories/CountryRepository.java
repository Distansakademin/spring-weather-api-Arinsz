package com.example.spring_mysql_api.repositories;
import com.example.spring_mysql_api.entities.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
    Optional<Country> findByCountryName(String countryName);
}




