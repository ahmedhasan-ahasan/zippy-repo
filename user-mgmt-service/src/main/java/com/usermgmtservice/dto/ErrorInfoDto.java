package com.usermgmtservice.dto;

import lombok.Data;

@Data
public class ErrorInfoDto {
	protected int errorCode;
	protected String errorMessage;
}
