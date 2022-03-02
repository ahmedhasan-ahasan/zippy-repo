package com.usermgmtservice.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;

import com.usermgmtservice.entities.Address;
import com.usermgmtservice.entities.SystemUser;
import com.usermgmtservice.entities.UserRole;

/**
 * 
 * @author ahmed
 * this Class to Test The systemUserRepository class
 */
@SpringBootTest
public class SystemUserRepositoryTest {
	
	@Autowired
	private SystemUserRepository systemUserRepository;
	
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
	long random;
	
	
	@BeforeAll
	static void init() {
	}
	@BeforeEach
	void setup() {
		random = new SecureRandom().nextInt(99999999);//nextLong(9999999999L);
		emailAddress = "paul736@gmail.com";
		systemUser = new SystemUser();
		address = new Address();
		addresses = new HashSet<>();
		userRole = new UserRole();
		
		date = new Date();
		systemUserName = "system";
		verifiedStatus = 0;
		status = "L";
		userRoleName = " Customer";
	}

	/**
	 * to register the customer successfully
	 */
	@Test
	void registerCustomerTest() {
		
		
		systemUser.setFirstName("David");
		systemUser.setLastName("Paul");
		systemUser.setEmailAddress(random+emailAddress);
		systemUser.setMobileNo(Long.toString(random));
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
		
		SystemUser insertedSystemUser = this.systemUserRepository.save(systemUser);
		assertThat(insertedSystemUser).hasFieldOrPropertyWithValue("lastName",insertedSystemUser.getLastName());

		
	}
	
	@Test
	void registerCustomerExceptionEmailIsNotUniqueTest() {
		
		systemUser.setFirstName("David");
		systemUser.setLastName("Paul");
		systemUser.setEmailAddress(emailAddress);
		systemUser.setMobileNo("9090987125");
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
	

		DataIntegrityViolationException exception =  Assertions.assertThrows(DataIntegrityViolationException.class, ()-> {
			this.systemUserRepository.save(systemUser);
		});	
		assertThat(exception).hasMessageContaining("could not execute statement");
	}
	
	@Test
	void registerCustomerExceptionMobileNoNotUniqueTest() {
		
		systemUser.setFirstName("David");
		systemUser.setLastName("Paul");
		systemUser.setEmailAddress("Z"+emailAddress);
		systemUser.setMobileNo("9090987126");
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
	

		DataIntegrityViolationException exception = Assertions.assertThrows(DataIntegrityViolationException.class, ()-> {
			this.systemUserRepository.save(systemUser);
		});	
		assertThat(exception).hasMessageContaining("could not execute statement");
	}

	
	@Test
	void countUserAccountByEmailAddressTest() {
		long actualCount = this.systemUserRepository.countByEmailAddress(emailAddress);
		assertEquals(1, actualCount);
	}
	
	@Test
	void countByMobileNoTest() {
		long actualCount = this.systemUserRepository.countByMobileNo("9894198941");
		assertEquals(0, actualCount);
	}

	@Test
	@Transactional
	@Rollback(false)
	void updateSystemUserTest() {
		Optional<SystemUser> opSystemUser = this.systemUserRepository.findByEmailAddress(emailAddress);
		
		SystemUser systemUser = opSystemUser.get();
		
		
		int updatedRecords = this.systemUserRepository.update(systemUser.getSystemUserId(), "A", 1, 1, date, date);
		
		opSystemUser = this.systemUserRepository.findByEmailAddress(emailAddress);
		systemUser = opSystemUser.get();
		assertEquals(1, updatedRecords);
	}

	
	
	@Test
	void findByEmailAddressTest(){
		Optional<SystemUser> optionalSystemUser = this.systemUserRepository.findByEmailAddress(emailAddress);
		SystemUser systemUser = optionalSystemUser.get();
		assertThat(systemUser).hasFieldOrPropertyWithValue("firstName", "David");
	}


	@AfterEach
	void destroy() {
		emailAddress = null;
		systemUser = null;
		address = null;
		addresses = null;
		userRole = null;
		
		date = null;
		systemUserName = null;
		verifiedStatus = 0;
		status = null;
		userRoleName = null;
		//System.out.println("After each..............");
	}
	@AfterAll
	static void release() {
		

		//System.out.println("After all...........");
	}
}
