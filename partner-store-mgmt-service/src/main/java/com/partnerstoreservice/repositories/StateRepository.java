package com.partnerstoreservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.partnerstoreservice.entities.State;

/**
 * @author ahmed
 */
public interface StateRepository extends JpaRepository<State, Long> {
	@Query("select state from State state where state.country.countryName = :countryName ")
	public List<State> findStatesInCountry(@Param("countryName") String countryName);
}
