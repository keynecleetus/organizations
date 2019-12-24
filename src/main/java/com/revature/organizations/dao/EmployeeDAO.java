package com.revature.organizations.dao;

import com.revature.organizations.dto.EmployeeDTO;
import com.revature.organizations.exceptions.DBException;


public interface EmployeeDAO {
	Boolean addEmployee(EmployeeDTO employee) throws DBException;

}
