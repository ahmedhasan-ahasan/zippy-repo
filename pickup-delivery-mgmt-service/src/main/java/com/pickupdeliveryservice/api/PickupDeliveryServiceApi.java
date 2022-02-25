package com.pickupdeliveryservice.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pickupdeliveryservice.dto.PackageTypeDto;
import com.pickupdeliveryservice.service.PickupDeliveryManagmentService;

/**
 * @author ahmed
 */

@RestController
@RequestMapping("/pickup")
public class PickupDeliveryServiceApi {

	@Autowired
	private PickupDeliveryManagmentService pickupDeliveryManagmentService;

	@GetMapping("/packages")
	public ResponseEntity<List<PackageTypeDto>> getPackageTypes() {
		System.out.println("calling the getPackageTypes>>>>>>>>>>>>>>>");
		return ResponseEntity.ok(this.pickupDeliveryManagmentService.getPackageTypes());
	}
}
