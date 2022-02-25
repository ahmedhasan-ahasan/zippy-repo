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

import lombok.Data;

/**
 * @author ahmed
 */
@Data
@Entity
@Table(name = "city")
public class City implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2479228299781405976L;
	@Id
	@Column(name = "city_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long cityId;
	@Column(name = "city_nm")
	protected String cityName;
	@Column(name = "created_by")
	protected String createdBy;
	@Column(name = "created_dt")
	protected Date createdDate;
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;
	@Column(name = "last_modified_dt")
	protected Date lastModifiedDate;

	@ManyToOne
	@JoinColumn(name = "state_id")
	protected State state;
}
