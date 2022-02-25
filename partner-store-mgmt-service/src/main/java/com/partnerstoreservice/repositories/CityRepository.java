package com.partnerstoreservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.partnerstoreservice.entities.City;

/**
* @author ahmed
*/
public interface CityRepository  extends JpaRepository<City, Long>{
	@Query("select city from City city where city.state.stateName = :stateName")
	public List<City> findCitiesInState(@Param("stateName") String stateName);
}
