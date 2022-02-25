package com.pickupdeliveryservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickupdeliveryservice.dto.PackageTypeDto;
import com.pickupdeliveryservice.repositories.PackageTypeRepository;

/**
 * @author ahmed
 */
@Service
public class PickupDeliveryManagmentServiceImpl implements PickupDeliveryManagmentService {

	@Autowired
	private PackageTypeRepository packageTypeRepository;

	@Override
	public List<PackageTypeDto> getPackageTypes() {
		System.out.println("getPackageTypes>>>>>>>>>>>>>>>>>");
		return this.packageTypeRepository.findAll().stream().map((packageType) -> {
			PackageTypeDto packageTypeDto = null;
			packageTypeDto = new PackageTypeDto();
			packageTypeDto.setPackageTypeId(packageType.getPackageTypeId());
			packageTypeDto.setPackageTypeName(packageType.getPackageTypeName());
			return packageTypeDto;
		}).collect(Collectors.toList());
	}
}
