package com.pickupdeliveryservice.dto;

import lombok.Data;

@Data
public class AddressDto {

	protected long addressId;
	protected String fullName;
	protected String addressLine1;
	protected String addressLine2;
	protected String city;
	protected String state;
	protected int zip;
	protected String country;

}
