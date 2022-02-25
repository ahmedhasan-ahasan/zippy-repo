package com.pickupdeliveryservice.service;

import java.util.List;

import com.pickupdeliveryservice.dto.PackageTypeDto;

/**
 * @author ahmed
 */
public interface PickupDeliveryManagmentService {

	public List<PackageTypeDto> getPackageTypes();

}
