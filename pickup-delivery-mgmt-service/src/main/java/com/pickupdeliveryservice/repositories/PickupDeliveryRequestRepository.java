package com.pickupdeliveryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickupdeliveryservice.entities.PickupDeliveryRequest;

/**
* @author ahmed
*/
public interface PickupDeliveryRequestRepository extends JpaRepository<PickupDeliveryRequest, Long>{

}
