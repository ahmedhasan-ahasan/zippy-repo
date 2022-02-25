package com.partnerstoreservice.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partnerstoreservice.dto.CityDto;
import com.partnerstoreservice.dto.ServiceAreaDto;
import com.partnerstoreservice.dto.StateDto;
import com.partnerstoreservice.dto.StoreDto;
import com.partnerstoreservice.service.StoreManagementService;

/**
* @author ahmed
*/

@RestController
@RequestMapping("/store")
public class StoreManagementApi {

	@Autowired
	private StoreManagementService storeManagementService;
	
	@GetMapping(value = "/states/{countryName}" , produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<StateDto> getStates(@PathVariable("countryName") String countryName){
		return this.storeManagementService.getStates(countryName);
	}
	
	@GetMapping(value = "/cities/{stateName}" , produces = {MediaType.APPLICATION_JSON_VALUE} )
	public List<CityDto> getCities(@PathVariable("stateName") String stateName){
		return this.storeManagementService.getCities(stateName);
				
	}
	
	@GetMapping(value = "/serviceAreas/{cityName}")
	public List<ServiceAreaDto> getServiceAreas(@PathVariable("cityName") String cityName){
		return this.storeManagementService.getServiceAreas(cityName);
	}
	
	@GetMapping(value = "/stores/{serviceAreaId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<StoreDto> getStoresInServiceArea(@PathVariable("serviceAreaId") long serviceAreaId){
		return this.storeManagementService.getStoresInServiceArea(serviceAreaId);
	}
}
