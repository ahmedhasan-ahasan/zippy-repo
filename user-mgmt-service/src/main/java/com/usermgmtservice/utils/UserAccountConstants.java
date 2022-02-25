package com.usermgmtservice.utils;

public interface UserAccountConstants {
	 String USER_SYSTEM = "system";
	 String USER_CUSTOMER  = "CUSTOMER";
	 int EMAIL_VERIFICATION_OTP_CODE_LENGTH = 12;
	 int MOBILE_VERIFICATION_OTP_CODE_LENGTH = 6;
	 String REGISTERED_USER_STATUS = "R";
	 String ACTIVE_USER_STATUS="A";
	 
	/**
	 * those are related to error codes
	 */
	 int ERROR_CODE_ACCOUNT_ALREADY_ACTIVATED = 2001;
	 int ERROR_CODE_OTP_MISMATCH = 2002;
	 int ERROR_CODE_ACCOUNT_NOT_FOUND = 2003;
	 int ERROR_CODE_OTP_ALREADY_VERIFIED = 2004;
	 int ERROR_CODE_UNKNOWN_VERIFICATION_TYPE = 2005; 		 
	 
	 int VERIFIED_STATUS=1;
	 String MOBILE_VERIFICATION_TYPE = "mobile";
	 String EMAIL_VERIFICATION_TYPE = "email";
	 
	 
	
}
