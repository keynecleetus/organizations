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

public class ActivationCode {


	private int id;


	public int getActivationCodes() {
		return activationCodes;
	}

	public void setActivationCodes(int activationCodes) {
		this.activationCodes = activationCodes;
	}

	private int organizationId;


	private int activationCodes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fromDate;


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date toDate;


	private long createdBy;

	@JsonIgnore

	private Date createdDate;
	

	private long modifiedBy;

	@JsonIgnore

	private LocalDate modifiedDate = LocalDate.now();

	

	private boolean status;

}
