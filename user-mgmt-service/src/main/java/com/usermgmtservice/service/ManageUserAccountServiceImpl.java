package com.usermgmtservice.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usermgmtservice.dto.AddressDto;
import com.usermgmtservice.dto.UserAccountActivationStatusDto;
import com.usermgmtservice.dto.UserAccountDto;
import com.usermgmtservice.entities.Address;
import com.usermgmtservice.entities.SystemUser;
import com.usermgmtservice.entities.UserRole;
import com.usermgmtservice.exceptions.OtpAlreadyVerifiedException;
import com.usermgmtservice.exceptions.OtpMismatchException;
import com.usermgmtservice.exceptions.UnknownVerificationTypeException;
import com.usermgmtservice.exceptions.UserAccountAlreadyActivatedException;
import com.usermgmtservice.exceptions.UserAccountNotFoundException;
import com.usermgmtservice.repositories.AddressRepository;
import com.usermgmtservice.repositories.SystemUserRepository;
import com.usermgmtservice.repositories.UserRoleRepository;
import com.usermgmtservice.utils.Mapper;
import com.usermgmtservice.utils.RandomGenerator;
import com.usermgmtservice.utils.UserAccountConstants;


/**
 * @author ahmed
 *
 */
@Service
public class ManageUserAccountServiceImpl implements ManageUserAccountService, UserAccountConstants {
	private static final Logger logger = LoggerFactory.getLogger(ManageUserAccountServiceImpl.class);
	@Autowired
	private SystemUserRepository systemUserRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	
	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	 

	@Transactional(readOnly = false)
	public long registerCustomer(UserAccountDto customerDto) {

		UserRole userRole = null;
		Set<Address> addresses = null;
		Address address = null;
		SystemUser systemUser = null;
		Date today = null;
		/**
		 * 1. Get the user role object
		 */
		today = new Date();
		
		logger.info("Getting the user role name: {}" ,USER_CUSTOMER);
		userRole = this.userRoleRepository.findUserRoleByRoleNameLike(USER_CUSTOMER);
		logger.debug("User role name {} has user role id {}", userRole.getRoleName(), userRole.getUserRoleId());

		

		/**
		 * 2. Create System User and insert it into db
		 */
		systemUser = new SystemUser();
		systemUser = Mapper.mapCustomerDtoToSystemUser(customerDto);
		/**
		 * TODO this length of the email verification code should be part of the
		 * constants
		 */
		
		systemUser.setPassword(bCryptPasswordEncoder.encode(systemUser.getPassword()));
		systemUser
				.setEmailVerificationOtpCode(RandomGenerator.generateEmailOtpCode(EMAIL_VERIFICATION_OTP_CODE_LENGTH));
		systemUser.setMobileNoOtpCode(RandomGenerator.generateMobileOtpCode(MOBILE_VERIFICATION_OTP_CODE_LENGTH));
		systemUser.setEmailVerificationOtpCodeGeneratedDate(today);
		systemUser.setMobileNoOtpCodeGeneratedDate(today);
		systemUser.setRegisteredDate(today);
		systemUser.setCreatedBy(USER_SYSTEM);
		systemUser.setCreatedDate(today);
		systemUser.setLastModifiedBy(USER_SYSTEM);
		systemUser.setLastModifiedDate(today);
		systemUser.setStatus(REGISTERED_USER_STATUS);
		//systemUser.setAddress(address);
		systemUser.setUserRole(userRole);
		
		logger.info("Saving the customer with firstName {}, and lastName {}", systemUser.getFirstName(),
				systemUser.getLastName());
		systemUser = this.systemUserRepository.save(systemUser);
		logger.debug("Customer user has been saved successfuly with system_user_id {} ", systemUser.getSystemUserId());
		
		/**
		 * 3. Create Address object and insert into db
		 */
		//addressDto = customerDto.getAddressDto();
		addresses = new HashSet<>();
		
		for(AddressDto addressDtoNew : customerDto.getAddressDtos()) {
			address = Mapper.mapAddressDtoToAddress(addressDtoNew);
			address.setCreatedBy(USER_SYSTEM);
			address.setCreatedDate(today);
			address.setLastModifiedBy(USER_SYSTEM);
			address.setLastModifiedDate(today);
			address.setSystemUser(systemUser);
			addresses.add(address);
		}
		
		
		logger.info("Saving the address into address table");
		this.addressRepository.saveAll(addresses);
		logger.debug("The address has been successfully saved with address_id {}", address.getAddressId());
		
		return systemUser.getSystemUserId();
	}


	@Override
	@Transactional(readOnly = true)
	public long countUserAccountByEmailAddress(String emailAddress) {
		return this.systemUserRepository.countByEmailAddress(emailAddress);
	}


	@Override
	@Transactional(readOnly = true)
	public long countUserAccountByMobileNo(String mobileNo) {
		return this.systemUserRepository.countByMobileNo(mobileNo);
	}


	@Override
	@Transactional(readOnly = false, rollbackFor = {OtpAlreadyVerifiedException.class, UserAccountNotFoundException.class})
	public UserAccountActivationStatusDto verifyOtpAndUpdateAccountStatus(long systemUserId,String verifcationCode, String verificationType) throws UserAccountNotFoundException, OtpMismatchException, UserAccountAlreadyActivatedException, OtpAlreadyVerifiedException, UnknownVerificationTypeException {
		Optional<SystemUser> optionalSystemUser = null;
		UserAccountActivationStatusDto userAccountActivationStatusDto = null;
		SystemUser systemUser = null;
		Date lastModifiedDate = null;
		Date activatedDate = null;
		
		if (!(verificationType.equals(MOBILE_VERIFICATION_TYPE) || verificationType.equals(EMAIL_VERIFICATION_TYPE))) {
			throw new UnknownVerificationTypeException(ERROR_CODE_UNKNOWN_VERIFICATION_TYPE,"unknown verification type");
		}
		
		optionalSystemUser = this.systemUserRepository.findById(systemUserId);
		/**
		 * Throw UserAccountNotFoundException if the user does not exist
		 */
		if(optionalSystemUser.isEmpty()) {
			throw new UserAccountNotFoundException(ERROR_CODE_ACCOUNT_NOT_FOUND, "user account not found");
		}
		
		systemUser = optionalSystemUser.get();
		
		if (systemUser.getStatus().equals(ACTIVE_USER_STATUS)){
			throw new UserAccountAlreadyActivatedException(ERROR_CODE_ACCOUNT_ALREADY_ACTIVATED,"User account is already activated");
		}
		
		userAccountActivationStatusDto = new UserAccountActivationStatusDto();
		userAccountActivationStatusDto.setSystemUserId(systemUser.getSystemUserId());
		userAccountActivationStatusDto.setMobileOtpVerificationCodeVerifiedStatus(systemUser.getMobileNoOtpCodeVerifiedStatus());
		userAccountActivationStatusDto.setEmailVerificationCodeVerifiedStatus(systemUser.getEmailVerificationOtpCodeVerifiedStatus());
		userAccountActivationStatusDto.setStatus(systemUser.getStatus());
		
		if (verificationType.equals(MOBILE_VERIFICATION_TYPE)) {
			logger.info("Mobile Verification type");
			if(systemUser.getMobileNoOtpCodeVerifiedStatus()==VERIFIED_STATUS) {
				throw new OtpAlreadyVerifiedException(ERROR_CODE_OTP_ALREADY_VERIFIED,"Otp already verified");
			}
			if (!(systemUser.getMobileNoOtpCode().equals(verifcationCode))){
				throw new OtpMismatchException(ERROR_CODE_OTP_MISMATCH, "otp mismatch");
			}else {
				logger.info("the mobile verification matched");
				userAccountActivationStatusDto.setMobileOtpVerificationCodeVerifiedStatus(VERIFIED_STATUS);
				if(systemUser.getEmailVerificationOtpCodeVerifiedStatus()==VERIFIED_STATUS) {
					userAccountActivationStatusDto.setStatus(ACTIVE_USER_STATUS);
					activatedDate = new Date();
				}
			}
		} else {
			logger.info("Email Verification type");
			if(systemUser.getEmailVerificationOtpCodeVerifiedStatus()==VERIFIED_STATUS) {
				throw new OtpAlreadyVerifiedException(ERROR_CODE_OTP_ALREADY_VERIFIED,"Otp already verified");
			}
			if (!(systemUser.getEmailVerificationOtpCode().equals(verifcationCode))){
				throw new OtpMismatchException(ERROR_CODE_OTP_MISMATCH, "otp mismatch");
			}else {
				logger.info("the email verification matched");
				userAccountActivationStatusDto.setEmailVerificationCodeVerifiedStatus(VERIFIED_STATUS);
				if(systemUser.getMobileNoOtpCodeVerifiedStatus()==VERIFIED_STATUS) {
					userAccountActivationStatusDto.setStatus(ACTIVE_USER_STATUS);
					activatedDate = new Date();
				}
			}
		}
		
		lastModifiedDate = new Date();
		logger.info("updating the record");
		this.systemUserRepository.update(userAccountActivationStatusDto.getSystemUserId(), userAccountActivationStatusDto.getStatus() ,userAccountActivationStatusDto.getMobileOtpVerificationCodeVerifiedStatus(), userAccountActivationStatusDto.getEmailVerificationCodeVerifiedStatus(),lastModifiedDate ,activatedDate);
		logger.info("The record has been successfully updated");
		
		return userAccountActivationStatusDto;
	}


	@Override
	public UserAccountDto getUserAccount(long userAccountId) {
		SystemUser systemUser = null;
		UserAccountDto userAccountDto = null;
		systemUser = this.systemUserRepository.getById(userAccountId);
		userAccountDto = Mapper.mapSystemUserToUserAccountDto(systemUser);
		return userAccountDto;
	}


	@Override
	public UserAccountDto getUserAccount(String emailAddress) throws UserAccountNotFoundException {
		Optional<SystemUser> systemUserOptional = null;
		SystemUser systemUser = null;
		UserAccountDto userAccountDto = null;
		UserRole userRole = null;
		List<Address> addresses = null;
		
		logger.info("getUserAccount using the email address");
		
		systemUserOptional = this.systemUserRepository.findByEmailAddress(emailAddress);
		if(systemUserOptional.isEmpty()) {
			throw new UserAccountNotFoundException(ERROR_CODE_ACCOUNT_NOT_FOUND,"user account not found");
		}
		systemUser= systemUserOptional.get();
		
		userAccountDto = Mapper.mapSystemUserToUserAccountDto(systemUser);
		addresses = this.addressRepository.findBySystemUser(systemUser.getSystemUserId());
		userAccountDto.setAddressDtos(Mapper.mapAddressesToAddressDtos(addresses));
		
		return userAccountDto;
	}
}
