package com.usermgmtservice.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.usermgmtservice.exceptions.OtpAlreadyVerifiedException;
import com.usermgmtservice.exceptions.OtpMismatchException;
import com.usermgmtservice.exceptions.UnknownVerificationTypeException;
import com.usermgmtservice.exceptions.UserAccountAlreadyActivatedException;
import com.usermgmtservice.exceptions.UserAccountNotFoundException;
import com.usermgmtservice.service.ManageUserAccountService;

/**
* @author ahmed
*/
@WebMvcTest
class UserAccountApiServiceTest {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private ManageUserAccountService manageUserAccountService;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); 
	}
	
	@Test
	void getCountByEmailAddressTest() throws Exception {
		String emailAddress = "test@gmail.com";
		long numberOfOccurance = 1;
		Mockito.when(manageUserAccountService.countUserAccountByEmailAddress(emailAddress)).thenReturn(numberOfOccurance);
		MvcResult result =  mockMvc.perform(get("/account/count/emailAddress").param("emailAddress", emailAddress)).andReturn();
		long responseResult = Long.parseLong(result.getResponse().getContentAsString());
		assertEquals(1, responseResult);
	}
	
	@Test
	void getCountByMobileNoTest() throws Exception {
		long expectedReturnCount = 1;
		String mobile = "+919894198941";
		Mockito.when(manageUserAccountService.countUserAccountByMobileNo(mobile)).thenReturn(expectedReturnCount);
		MvcResult mvcResult = mockMvc.perform(get("/account/count/mobileNo").param("mobileNo", mobile)).andExpect(status().isOk()).andReturn();
		long actualReturnedCount = Long.parseLong(mvcResult.getResponse().getContentAsString());
		assertEquals(expectedReturnCount, actualReturnedCount);
	}
	
	void getUserAccountByUserAccountIdTest() {
		//TODO write the test method
	}
	
	void getUserAccountTestByEmailAddressTest() {
		//TODO write the test method
	}
	
	void registerCustomerTest() {
		//TODO write the test method
	}
	
	void getUserAccountByEmailAddress() {
		//TODO write the test method
	}
	
	void getUserAccountByEmailAddressTest(){
		//TODO write the test method
	}
	
	void verifyOtpAndUpdateAccountStatus() {
		/**
		 * TODO write the test method, here we have to take the considerations of all exceptions :
		 *  UserAccountNotFoundException, OtpMismatchException, UserAccountAlreadyActivatedException,
			OtpAlreadyVerifiedException, UnknownVerificationTypeException
		 */
	}
	
}
