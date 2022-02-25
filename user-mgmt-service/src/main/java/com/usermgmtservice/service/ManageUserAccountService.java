package com.usermgmtservice.service;

import com.usermgmtservice.dto.UserAccountActivationStatusDto;
import com.usermgmtservice.dto.UserAccountDto;
import com.usermgmtservice.exceptions.OtpAlreadyVerifiedException;
import com.usermgmtservice.exceptions.OtpMismatchException;
import com.usermgmtservice.exceptions.UnknownVerificationTypeException;
import com.usermgmtservice.exceptions.UserAccountAlreadyActivatedException;
import com.usermgmtservice.exceptions.UserAccountNotFoundException;

public interface ManageUserAccountService {
	public long registerCustomer(UserAccountDto userAccountDto);

	public long countUserAccountByEmailAddress(String emailAddress);

	public long countUserAccountByMobileNo(String mobileNo);

	public UserAccountActivationStatusDto verifyOtpAndUpdateAccountStatus(long systemUserId, String verificationCode,
			String verificationType) throws UserAccountNotFoundException, OtpMismatchException,
			UserAccountAlreadyActivatedException, OtpAlreadyVerifiedException, UnknownVerificationTypeException;
	
	public UserAccountDto getUserAccount(long userAccountId);
	public UserAccountDto getUserAccount(String emailAddress) throws UserAccountNotFoundException;
}
