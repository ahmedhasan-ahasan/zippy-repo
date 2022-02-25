package com.usermgmtservice.exceptions;

public class OtpAlreadyVerifiedException extends UserAccountException {

	public OtpAlreadyVerifiedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OtpAlreadyVerifiedException(int errorCode, String errorMessage) {
		super(errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

	public OtpAlreadyVerifiedException(Throwable cause, boolean enableSuppression, boolean writableStackTrace,
			int errorCode, String errorMessage) {
		super(cause, enableSuppression, writableStackTrace, errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

	public OtpAlreadyVerifiedException(Throwable cause, int errorCode, String errorMessage) {
		super(cause, errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}
	
}
