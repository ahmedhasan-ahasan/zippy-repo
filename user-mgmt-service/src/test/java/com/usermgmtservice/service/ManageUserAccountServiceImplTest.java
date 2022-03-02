package com.usermgmtservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.usermgmtservice.dto.UserAccountDto;
import com.usermgmtservice.entities.Address;
import com.usermgmtservice.entities.SystemUser;
import com.usermgmtservice.entities.UserRole;
import com.usermgmtservice.exceptions.UserAccountNotFoundException;
import com.usermgmtservice.repositories	.SystemUserRepository;

/**
 * This class is to test the UserManagerServiceImpl
 * @author ahmed
 *
 */

@SpringBootTest
public class ManageUserAccountServiceImplTest {

	@Autowired
	private ManageUserAccountService manageUserAccountService;
	
	@MockBean
	private SystemUserRepository systemUserRepository;
	
	UserAccountDto userAccountDto = null;

	
	private String emailAddress = null;
	private SystemUser systemUser;
	private Set<Address> addresses = null;
	private Address address = null;
	private UserRole userRole = null;
	
	Date date  = null;
	String systemUserName = null ;
	int verifiedStatus;
	String status = null;
	String userRoleName = null;
	
	@BeforeEach
	void setup() {
		
		
		address = new Address();
		addresses = new HashSet<>();
		userRole = new UserRole();
		
		
		
		status = "L";
		date = new Date();
		verifiedStatus = 0;
		systemUserName = "system";
		userRoleName = " Customer";
		systemUser = new SystemUser();
		emailAddress = "paul736@gmail.com";
		
		systemUser.setFirstName("David");
		systemUser.setLastName("Paul");
		systemUser.setEmailAddress(emailAddress);
		systemUser.setMobileNo("9894198941");
		systemUser.setPassword("welcome1");
		systemUser.setEmailVerificationOtpCode("123456");
		systemUser.setMobileNoOtpCode("1234");
		systemUser.setEmailVerificationOtpCodeVerifiedStatus(verifiedStatus);
		systemUser.setMobileNoOtpCodeVerifiedStatus(verifiedStatus);
		systemUser.setStatus(status);
		systemUser.setCreatedBy(systemUserName);
		systemUser.setCreatedDate(date);
		systemUser.setLastModifiedBy(systemUserName);
		systemUser.setLastModifiedDate(date);
		systemUser.setRegisteredDate(date);
		
		systemUser.setEmailVerificationOtpCodeGeneratedDate(date);
		systemUser.setMobileNoOtpCodeGeneratedDate(date);
		
		address.setFullName("David Paul");
		address.setAddressLine1("Matrivanam");
		address.setAddressLine2("SR Nagar");
		address.setCity("Hyderabad");
		address.setState("Telengana");
		address.setZip(600123);
		address.setCountry("India");
		
		addresses.add(address);
		systemUser.setAddresses(addresses);
		
		userRole.setUserRoleId(2);
		userRole.setRoleName(userRoleName);
		
		systemUser.setUserRole(userRole);
	}
	
	/**
	 * Count user Accounts by EmailAddress
	 */
	@Test
	void countUserAccountByEmailAddressTest() {
		when(this.systemUserRepository.countByEmailAddress(emailAddress)).thenReturn(1L);
		
		long actualCount = this.manageUserAccountService.countUserAccountByEmailAddress(emailAddress);
		assertEquals(1,actualCount);
	}
	
	/**
	 * get User Account
	 * It returns one user account
	 */
	@Test
	void getUserAccountByEmailAddressTest() {
		UserAccountDto returnedUserAccountDto = null;
		Optional<SystemUser> optionalSystemUser = Optional.of(systemUser);
		
		
		when(this.systemUserRepository.findByEmailAddress(emailAddress)).thenReturn(optionalSystemUser);
		
		try {
			returnedUserAccountDto =  this.manageUserAccountService.getUserAccount(emailAddress);
		} catch (UserAccountNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(optionalSystemUser.get().getSystemUserId(), returnedUserAccountDto.getUserAccountId());
	}
	
	/**
	 * get user Account
	 * It throws exception as the user Account is empty(User Account Not Found)
	 */
	@Test
	void getUserAccountByEmailAddressWithUserAccountNotFoundExceptionTest() {

		Optional<SystemUser> optionalSystemUser = Optional.empty();
		boolean flag = false; 
		
		when(this.systemUserRepository.findByEmailAddress(emailAddress)).thenReturn(optionalSystemUser);
		try {
			this.manageUserAccountService.getUserAccount(emailAddress);
		} catch (UserAccountNotFoundException e) {
			flag = true;
		}
		assertEquals(true,flag);
	}
	
	
	void registerCustomerSuccess() {
		//TODO write the test code
	}
	
	void registerCustomerWithExceptionTest() {
		//TODO write the test code
	}
	
	void countUserAccountByMobileNoTest() {
		//TODO write the test code
	}
	
	void verifyOtpAndUpdateAccountStatusTest(){
		//TODO create several cases verify email, verify mobile no, though exceptions like mismatch exception, and userAccountNotFoundException
	}

	void getUserAccountByUserAccountIdTest() {
		//TODO write the test code
	}
	void getUserAccountByUserAccountIdWithUserAccountNotFoundExceptionTest() {
		//TODO write the test code
	}

}
