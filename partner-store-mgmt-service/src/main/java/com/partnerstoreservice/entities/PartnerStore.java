package com.partnerstoreservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "partner_store")
@Getter
@Setter
public class PartnerStore implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "partner_store_id")
	protected long partnerStoreId;
	@Column(name = "business_nm")
	protected String businessName;
	@Column(name = "business_license_no")
	protected String businessLicenseNo;
	@Column( name = "gst_no")
	protected String gstNo;
	@Column(name = "owner_first_nm")
	protected String ownerFirstName;
	@Column(name = "owner_last_nm")
	protected String ownerLastName;
	
	protected String createdBy;
	@Column(name = "created_dt")
	protected Date createdDate;
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;
	@Column(name = "last_modified_dt")
	protected Date lastModifiedDate;  
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="owner_contact_details_id" )
	protected ContactDetails ownerContactDetails;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="primary_contact_details_id" )
	protected ContactDetails primaryContactDetails;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="secondary_contact_details_id" )
	protected ContactDetails secondaryContactDetails;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "registered_address_id")
	protected Address registeredAddress;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "partner_store_logo_file_id")
	protected FileAttachment fileAttachment;
	
	
	
	
	
}
