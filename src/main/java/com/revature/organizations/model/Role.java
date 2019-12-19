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
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "role")
	private String roles;

	@Column(name = "created_by")
	private long createdBy;

	@JsonIgnore
	@Column(name = "created_date")
	private LocalDate createdDate = LocalDate.now();

	@Column(name = "status")
	private boolean status;

}
