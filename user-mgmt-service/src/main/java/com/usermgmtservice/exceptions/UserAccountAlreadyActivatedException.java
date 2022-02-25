package com.usermgmtservice.exceptions;

public class UserAccountAlreadyActivatedException extends UserAccountException{

	public UserAccountAlreadyActivatedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAccountAlreadyActivatedException(int errorCode, String errorMessage) {
		super(errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

	public UserAccountAlreadyActivatedException(Throwable cause, boolean enableSuppression, boolean writableStackTrace,
			int errorCode, String errorMessage) {
		super(cause, enableSuppression, writableStackTrace, errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

	public UserAccountAlreadyActivatedException(Throwable cause, int errorCode, String errorMessage) {
		super(cause, errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

}
