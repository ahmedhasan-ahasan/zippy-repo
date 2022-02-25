package com.usermgmtservice.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Setter
@Getter
@ToString
public class SystemUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5428342679612931629L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "system_user_id")
	protected long systemUserId;
	@Column(name = "email_address")
	protected String emailAddress;
	@Column(name = "mobile_nbr")
	protected String mobileNo;
	@Column(name = "first_nm")
	protected String firstName;
	@Column(name = "last_nm")
	protected String lastName;
	protected String password;
	@Column(name = "email_verification_otp_code")
	protected String emailVerificationOtpCode;
	@Column(name = "mobile_nbr_otp_code")
	protected String mobileNoOtpCode;
	@Column(name = "email_verification_otp_code_generated_dt")
	protected Date emailVerificationOtpCodeGeneratedDate;
	@Column(name = "mobile_nbr_otp_code_generated_dt")
	protected Date mobileNoOtpCodeGeneratedDate;
	@Column(name = "mobile_nbr_otp_code_verified_status")
	protected int mobileNoOtpCodeVerifiedStatus;
	@Column(name = "email_verification_otp_code_verified_status")
	protected int emailVerificationOtpCodeVerifiedStatus;
	protected String status;
	@Column(name = "registered_dt")
	protected Date registeredDate;
	@Column(name = "activated_dt")
	protected Date activatedDate;
	
	@Column(name = "created_by")
	protected String createdBy;
	@Column(name = "created_dt")
	protected Date createdDate;
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;
	@Column(name = "last_modified_dt")
	protected Date lastModifiedDate;

	@ManyToOne
	@JoinColumn(name = "user_role_id")
	protected UserRole userRole;

	@OneToMany(mappedBy = "systemUser", fetch = FetchType.LAZY)
	protected Set<Address> addresses;

}
