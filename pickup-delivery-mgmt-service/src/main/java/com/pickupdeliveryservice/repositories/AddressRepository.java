package com.pickupdeliveryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickupdeliveryservice.entities.Address;

/**
* @author ahmed
*/
public interface AddressRepository extends JpaRepository<Address, Long>{

}
