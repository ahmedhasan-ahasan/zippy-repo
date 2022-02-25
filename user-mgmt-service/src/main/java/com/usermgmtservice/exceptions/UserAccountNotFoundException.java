package com.usermgmtservice.exceptions;

public class UserAccountNotFoundException extends UserAccountException {

	public UserAccountNotFoundException() {
		super();
	}

	public UserAccountNotFoundException(int errorCode, String errorMessage) {
		super(errorCode, errorMessage);

	}

	public UserAccountNotFoundException(Throwable cause, boolean enableSuppression, boolean writableStackTrace,
			int errorCode, String errorMessage) {
		super(cause, enableSuppression, writableStackTrace, errorCode, errorMessage);
	}

	public UserAccountNotFoundException(Throwable cause, int errorCode, String errorMessage) {
		super(cause, errorCode, errorMessage);
	}

}
