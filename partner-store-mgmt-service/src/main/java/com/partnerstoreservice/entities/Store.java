package com.partnerstoreservice.entities;

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
@Table(name = "stores")
@Setter
@Getter
public class Store implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	protected long storeId;
	@Column(name = "store_name")
	protected String storeName;

	protected String createdBy;
	@Column(name = "created_dt")
	protected Date createdDate;
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;
	@Column(name = "last_modified_dt")
	protected Date lastModifiedDate;

	@ManyToOne
	@JoinColumn(name = "partner_store_id")
	protected PartnerStore partnerStore;
	@ManyToOne
	@JoinColumn(name = "service_area_id")
	protected ServiceArea serviceArea;
	@ManyToOne
	@JoinColumn(name = "primary_contact_details_id")
	protected ContactDetails primaryContactDetails;
	@ManyToOne
	@JoinColumn(name = "secondary_contact_details_id")
	protected ContactDetails secondaryContactDetails;
	@ManyToOne
	@JoinColumn(name = "store_address_id")
	protected Address storeAddress;

}
