package com.usermgmtservice.dto;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class UserAccountDto {
	private long userAccountId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String mobileNo;
	private String password;
	private List<AddressDto> addressDtos;
	private UserRoleDto userRoleDto;

}
