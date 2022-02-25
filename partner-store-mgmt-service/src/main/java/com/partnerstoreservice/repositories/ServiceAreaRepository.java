package com.partnerstoreservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.partnerstoreservice.entities.ServiceArea;

/**
* @author ahmed
*/
public interface ServiceAreaRepository extends JpaRepository<ServiceArea, Long>{
	@Query("select area from ServiceArea area where area.city.cityName = :cityName")
	public List<ServiceArea> findServiceAreasInCity(@Param("cityName") String cityName);
}
