package com.revature.organizations.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "organization_details")
public class OrganizationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "alias_name")
	private String aliasName;

	@Column(name = "type")
	private String type;

	@Column(name = "salesforce_id")
	private String salesforceId;

	@Column(name = "enable_user_verification")
	private boolean enableUserVerification;

	@Column(name = "sign_up_page")
	private boolean signUpPage;

	@Column(name = "theme_id")
	private int themeId;

	@Column(name = "created_by")
	private long createdBy;

	@JsonIgnore
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "modified_by")
	private long modifiedBy;

	@JsonIgnore
	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;


	@Column(name = "status")
	private boolean status;

}
