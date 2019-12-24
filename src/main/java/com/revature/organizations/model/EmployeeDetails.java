package com.revature.organizations.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "employee_details")
public class EmployeeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile_no")
	@Size(min = 10, max = 10)
	private long mobileNo;

	@Column(name = "salesforce_id")
	private String salesforceId;

	@Column(name = "title")
	private String title;

	@Column(name = "timezone")
	private String timezone;

	@Column(name = "country")
	private String country;

	@Column(name = "created_by")
	private String createdBy;

	@JsonIgnore
	@Column(name = "created_date")
	private LocalDate createdDate = LocalDate.now();

	@Column(name = "modified_by")
	private String modifiedBy;

	@JsonIgnore
	@Column(name = "modified_date")
	private LocalDate modifiedDate = LocalDate.now();

	@Column(name = "status")
	private boolean status;

}
