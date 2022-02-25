package com.partnerstoreservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.partnerstoreservice.entities.Store;

/**
* @author ahmed
*/
public interface StoreRepository extends JpaRepository<Store, Long>{
	@Query("select store from Store store where store.serviceArea.serviceAreaId = :serviceAreaId")
	public List<Store> findStoresByServiceArea(@Param("serviceAreaId") long serviceAreaId);
}
