package com.usermgmtservice.dto;

import lombok.Data;

@Data
public class UserAccountActivationStatusDto {
	protected long systemUserId;
	protected int mobileOtpVerificationCodeVerifiedStatus;
	protected int emailVerificationCodeVerifiedStatus;
	protected String status;
}
