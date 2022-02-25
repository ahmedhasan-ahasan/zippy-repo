package com.usermgmtservice.exceptions;

public class UnknownVerificationTypeException extends UserAccountException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4936694852647643942L;

	public UnknownVerificationTypeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnknownVerificationTypeException(int errorCode, String errorMessage) {
		super(errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

	public UnknownVerificationTypeException(Throwable cause, boolean enableSuppression, boolean writableStackTrace,
			int errorCode, String errorMessage) {
		super(cause, enableSuppression, writableStackTrace, errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

	public UnknownVerificationTypeException(Throwable cause, int errorCode, String errorMessage) {
		super(cause, errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

}
