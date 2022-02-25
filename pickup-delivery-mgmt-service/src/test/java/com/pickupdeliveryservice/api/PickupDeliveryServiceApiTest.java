package com.pickupdeliveryservice.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pickupdeliveryservice.dto.PackageTypeDto;
import com.pickupdeliveryservice.service.PickupDeliveryManagmentService;


/**
 * @author ahmed
 */

@WebMvcTest
public class PickupDeliveryServiceApiTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PickupDeliveryManagmentService pickupDeliveryManagmentService;

	private List<PackageTypeDto> packageTypesDto;
	
	@Autowired
	private WebApplicationContext context;
	
	@BeforeEach
	private void setup() {
		packageTypesDto = new ArrayList<>();
		for(int i = 0; i<1 ; i++) {
			PackageTypeDto packageTypeDto = new PackageTypeDto();
			packageTypeDto.setPackageTypeId(i+1);
			packageTypeDto.setPackageTypeName("packageType "+i+1);
			packageTypesDto.add(packageTypeDto);
		}
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); 

		
	}

	@Test
	void getPackageTypesTest() throws Exception {
		
		when(this.pickupDeliveryManagmentService.getPackageTypes()).thenReturn(packageTypesDto);
		this.mockMvc.perform(get("/pickup/packages")).andExpect(status().isOk()).andExpect(jsonPath("$",Matchers.hasSize(1)));
		//String result = mvcResult.getResponse().getContentAsString();
		//System.out.println("executing with result " + result + " >>>>>>>>>>>>>>");
	}
	
	
	
	@Test
	void getCountByEmailAddressTest() throws Exception {
		Mockito.when(this.pickupDeliveryManagmentService.getPackageTypes()).thenReturn(packageTypesDto);
		//MvcResult result =  mockMvc.perform(get("/pickup/packages1")).
	    mockMvc.perform(get("/pickup/packages")).andExpect(status().isOk());
		//String responseResult = result.getResponse().getContentAsString();
		//System.out.println("reponseresult >>>> "+ responseResult + " >>>>>>");
		
	}

}
