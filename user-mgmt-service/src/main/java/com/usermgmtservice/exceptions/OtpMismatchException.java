package com.usermgmtservice.exceptions;

public class OtpMismatchException extends UserAccountException {

	public OtpMismatchException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OtpMismatchException(int errorCode, String errorMessage) {
		super(errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

	public OtpMismatchException(Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode,
			String errorMessage) {
		super(cause, enableSuppression, writableStackTrace, errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

	public OtpMismatchException(Throwable cause, int errorCode, String errorMessage) {
		super(cause, errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}
	

}
