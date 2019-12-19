package com.revature.organizations.model;

import java.sql.Timestamp;
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
@Table(name = "domain")
public class Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "organization_id")
	private int organizationId;

	@Column(name = "domains")
	private String domains;

	@Column(name = "created_by")
	private long createdBy;

	@JsonIgnore
	@Column(name = "created_date")
	private Timestamp createdDate;
	
	@Column(name = "modified_by")
	private long modifiedBy;

	@JsonIgnore
	@Column(name = "modified_date")
	private LocalDate modifiedDate = LocalDate.now();


	@Column(name = "status")
	private boolean status;

}
