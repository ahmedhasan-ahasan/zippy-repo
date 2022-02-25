package com.pickupdeliveryservice.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pickupdeliveryservice.dto.PackageTypeDto;
import com.pickupdeliveryservice.repositories.PackageTypeRepository;

/**
 * @author ahmed
 */

@SpringBootTest
public class PickupDeliveryManagmentServiceImpl {

	@Autowired
	private PickupDeliveryManagmentService pickupDeliveryManagmentService;

	@MockBean
	private PackageTypeRepository packageTypeRepository;

	private List<PackageTypeDto> packageTypeDtos;

	void setup() {
		this.packageTypeDtos = new ArrayList<>();
		PackageTypeDto packageTypeDto = null;
		for(int i = 0 ; i< 1;i++) {
			 packageTypeDto = new PackageTypeDto();
			 packageTypeDto.setPackageTypeId(i+1);
			 packageTypeDto.setPackageTypeName("package type " + i+1);
			 this.packageTypeDtos.add(packageTypeDto);
		}
	}

	void getPackageTypes() {
		//when(this.pickupDeliveryManagmentService.getPackageTypes()).thenReturn(packageTypeDtos);
		//TODO check what to do next the above line seems to be wrong we should call repository
		
	}
}
