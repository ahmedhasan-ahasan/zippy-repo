package com.usermgmtservice.exceptions;

import com.usermgmtservice.dto.ErrorInfoDto;

public class UserAccountException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -591169136507677996L;
	protected ErrorInfoDto errorInfoDto;

	public UserAccountException() {
		super();
	}

	public UserAccountException(Throwable cause, boolean enableSuppression,
			boolean writableStackTrace,int errorCode, String errorMessage) {
		this.errorInfoDto = formErrorInfoDto(errorCode, errorMessage);
	}

	public UserAccountException(int errorCode,String errorMessage) {
		this.errorInfoDto = formErrorInfoDto(errorCode, errorMessage);
	}

	public UserAccountException(Throwable cause,int errorCode, String errorMessage) {
		this.errorInfoDto = formErrorInfoDto(errorCode, errorMessage);
	}
	
	private ErrorInfoDto formErrorInfoDto(int errorCode, String errorMessage) {
		ErrorInfoDto errorInfoDto = null;
		errorInfoDto = new ErrorInfoDto();
		errorInfoDto.setErrorCode(errorCode);
		errorInfoDto.setErrorMessage(errorMessage);
		return errorInfoDto;
		
	}
	
	public ErrorInfoDto getErrorInfoDto() {
		return errorInfoDto;
	}
}
