package com.pickupdeliveryservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
* @author ahmed
*/
@Entity
@Table(name = "package_types")
@Data	
public class PackageType implements Serializable{
	
	private static final long serialVersionUID = -3352137876978114162L;
	@Id
	@Column(name = "package_type_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long packageTypeId;
	@Column(name = "package_type_nm")
	protected String packageTypeName;
	protected String description;
	protected double maxWeightAllowed;
	
	protected String createdBy;
	@Column(name = "created_dt")
	protected Date createdDate;
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;
	@Column(name = "last_modified_dt")
	protected Date lastModifiedDate;
	
}
