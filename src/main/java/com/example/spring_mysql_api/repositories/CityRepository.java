package com.example.spring_mysql_api.repositories;
import com.example.spring_mysql_api.entities.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    List<City> findByCountryId(Long countryId);
}

