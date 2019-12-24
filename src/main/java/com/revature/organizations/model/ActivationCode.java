package com.revature.organizations.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "activation_code")
public class ActivationCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "organization_id")
	private int organizationId;

	@Column(name = "activation_code")
	private int activationCodes;

	@Column(name = "from_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fromDate;

	@Column(name = "to_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date toDate;

	@Column(name = "created_by")
	private long createdBy;

	@JsonIgnore
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modified_by")
	private long modifiedBy;

	@JsonIgnore
	@Column(name = "modified_date")
	private LocalDate modifiedDate = LocalDate.now();

	
	@Column(name = "status")
	private boolean status;

}
