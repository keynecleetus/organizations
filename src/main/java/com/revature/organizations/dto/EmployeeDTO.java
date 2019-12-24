package com.revature.organizations.dto;

import java.util.List;

import com.revature.organizations.model.EmployeeDetails;
import com.revature.organizations.model.EmployeeRole;

import lombok.Data;

@Data
public class EmployeeDTO {

	
	private EmployeeDetails employee;
	
	private List<EmployeeRole> employeeRole;

}
