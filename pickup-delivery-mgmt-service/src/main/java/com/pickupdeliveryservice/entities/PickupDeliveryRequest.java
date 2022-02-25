package com.pickupdeliveryservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
* @author ahmed
*/

@Entity
@Table (name = "pickup_delivery_request")
@Setter
@Getter
public class PickupDeliveryRequest implements Serializable{
	
	private static final long serialVersionUID = -3185876015039992255L;
	@Id
	@Column(name = "pickup_delivery_request_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long pickupDeliveryRequestId;
	@Column(name = "customer_id")
	protected String customerId;
	@Column(name = "receiver_nm")
	protected String ReceiverName;
	@Column(name = "receiver_mobile_nbr")
	protected String receiverMobileNo;
	@Column(name = "pickup_otp_code")
	protected String pickupOtpCode;
	@Column(name = "delivery_otp_code")
	protected String DeliveryOtpCode;
	@Column(name = "pickup_delivery_request_dt")
	protected Date pickupDeliveryRequestDate;
	@Column(name = "expected_delivery_dt")
	protected Date expectedDeliveryDate;
	@Column(name = "parcel_weight")
	protected double parcelWeight;
	@Column(name = "customer_notes")
	protected String customerNotes;
	protected double amount;
	@Column(name = "delivery_tracking_no")
	protected String deliveryTrackingNo;
	protected String status;
	
	protected String createdBy;
	@Column(name = "created_dt")
	protected Date createdDate;
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;
	@Column(name = "last_modified_dt")
	protected Date lastModifiedDate;
	
	@ManyToOne
	@JoinColumn(name = "pickup_address_id")
	protected Address pickupAddress;
	@ManyToOne
	@JoinColumn(name = "delivery_address_id")
	protected Address deliveryAddress;
	
}
