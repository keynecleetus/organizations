package com.revature.organizations.dao;

import com.revature.organizations.dto.EmployeeDTO;
import com.revature.organizations.exceptions.DBException;
import com.revature.organizations.model.EmployeeDetails;
import com.revature.organizations.model.EmployeeRole;

public interface EmployeeDAO {
	Boolean addEmployee(EmployeeDTO employee) throws DBException;

}
