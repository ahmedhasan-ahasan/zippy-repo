package com.pickupdeliveryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickupdeliveryservice.entities.PackageType;

/**
 * @author ahmed
 */
public interface PackageTypeRepository extends JpaRepository<PackageType, Long> {

}
