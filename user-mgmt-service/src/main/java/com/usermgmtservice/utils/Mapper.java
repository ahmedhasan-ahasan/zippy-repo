package com.usermgmtservice.utils;

import java.util.ArrayList;
import java.util.List;

import com.usermgmtservice.dto.AddressDto;
import com.usermgmtservice.dto.UserAccountDto;
import com.usermgmtservice.dto.UserRoleDto;
import com.usermgmtservice.entities.Address;
import com.usermgmtservice.entities.SystemUser;

public class Mapper {
	public static SystemUser mapCustomerDtoToSystemUser(UserAccountDto userAccountDto) {
		SystemUser systemUser = null;
		systemUser = new SystemUser();
		systemUser.setFirstName(userAccountDto.getFirstName());
		systemUser.setLastName(userAccountDto.getLastName());
		systemUser.setEmailAddress(userAccountDto.getEmailAddress());
		systemUser.setMobileNo(userAccountDto.getMobileNo());
		systemUser.setPassword(userAccountDto.getPassword());
		return systemUser;
	}

	public static List<AddressDto> mapAddressesToAddressDtos(List<Address> addresses) {
		
		List<AddressDto> addressDtos = null;
		AddressDto addressDto = null;
		 
		addressDtos = new ArrayList<AddressDto>();
		addressDto = new AddressDto();
		
		for (Address address : addresses) {
			addressDto = mapAddressToAddressDto(address);
			addressDtos.add(addressDto);
		}
		return addressDtos;

	}

	private static AddressDto mapAddressToAddressDto(Address address) {
		AddressDto addressDto = null;
		addressDto = new AddressDto();
		addressDto.setAddressId(address.getAddressId());
		addressDto.setFullName(address.getFullName());
		addressDto.setAddressLine1(address.getAddressLine1());
		addressDto.setAddressLine2(address.getAddressLine2());
		addressDto.setCity(address.getCity());
		addressDto.setState(address.getState());
		addressDto.setZip(address.getZip());
		addressDto.setCountry(address.getCountry());
		return addressDto;
	}

	public static Address mapAddressDtoToAddress(AddressDto addressDto) {
		Address address = null;
		address = new Address();
		address.setFullName(addressDto.getFullName());
		address.setAddressLine1(addressDto.getAddressLine1());
		address.setAddressLine2(addressDto.getAddressLine2());
		address.setCity(addressDto.getCity());
		address.setState(addressDto.getState());
		address.setZip(addressDto.getZip());
		address.setCountry(addressDto.getCountry());
		return address;
	}

	public static UserAccountDto mapSystemUserToUserAccountDto(SystemUser systemUser) {
		// AddressDto addressDto = null;
		UserRoleDto userRoleDto = null;
		UserAccountDto userAccountDto = null;

		/*
		 * addressDto = new AddressDto();
		 * addressDto.setAddressLine1(systemUser.getAddress().getAddressLine1());
		 * addressDto.setAddressLine2(systemUser.getAddress().getAddressLine2());
		 * addressDto.setCity(systemUser.getAddress().getCity());
		 * addressDto.setState(systemUser.getAddress().getState());
		 * addressDto.setZip(systemUser.getAddress().getZip());
		 * addressDto.setCountry(systemUser.getAddress().getCountry());
		 */

		userAccountDto = new UserAccountDto();
		userAccountDto.setUserAccountId(systemUser.getSystemUserId());
		userAccountDto.setFirstName(systemUser.getFirstName());
		userAccountDto.setLastName(systemUser.getLastName());
		userAccountDto.setPassword(systemUser.getPassword());
		userAccountDto.setMobileNo(systemUser.getMobileNo());
		userAccountDto.setEmailAddress(systemUser.getEmailAddress());
		// userAccountDto.setAddressDto(addressDto);

		userRoleDto = new UserRoleDto();
		userRoleDto.setRoleId(systemUser.getUserRole().getUserRoleId());
		userRoleDto.setRoleName(systemUser.getUserRole().getRoleName());
		userAccountDto.setUserRoleDto(userRoleDto);

		return userAccountDto;
	}

}
