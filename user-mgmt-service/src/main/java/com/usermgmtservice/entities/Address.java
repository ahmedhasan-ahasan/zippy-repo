package com.usermgmtservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address implements Serializable {
	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long addressId;
	@Column(name = "fullname")
	protected String fullName;
	@Column(name = "address_line1")
	protected String addressLine1;
	@Column(name = "address_line2")
	protected String addressLine2;
	protected String city;
	protected String state;
	protected int zip;
	protected String country;
	@Column(name = "created_by")
	protected String createdBy;
	@Column(name = "created_dt")
	protected Date createdDate;
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;
	@Column(name = "last_modified_dt")
	protected Date lastModifiedDate;
	
	@ManyToOne
	@JoinColumn(name = "system_user_id")
	protected SystemUser systemUser;
}
