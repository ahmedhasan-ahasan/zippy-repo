package com.pickupdeliveryservice.dto;

import java.util.Date;

import lombok.Data;

/**
 * @author ahmed
 */

@Data	
public class PickupDeliveryRequestDto {

	protected String customerId;
	protected String ReceiverName;
	protected String receiverMobileNo;
	protected String pickupOtpCode;
	protected Date pickupDeliveryRequestDate;
	protected double parcelWeight;
	protected String customerNotes;
	protected String status;
	protected AddressDto pickupAddress;
	protected AddressDto deliveryAddress;

}
