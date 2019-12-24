package com.revature.organizations.model;

import java.time.LocalDate;


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
@Table(name = "employee_role")
public class EmployeeRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "employee_id")
	private long employeeId;

	@Column(name = "organization_id")
	private int organizationId;

	@Column(name = "role_id")
	private int roleId;

	@Column(name = "created_by")
	private long createdBy;

	@JsonIgnore
	@Column(name = "created_date")
	private LocalDate createdDate = LocalDate.now();

	@Column(name = "modified_by")
	private int modifiedBy;

	@JsonIgnore
	@Column(name = "modified_date")
	private LocalDate modifiedDate = LocalDate.now();

}
