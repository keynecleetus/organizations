package com.revature.organizations.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.organizations.dao.EmployeeDAO;
import com.revature.organizations.dao.EmployeeDAOImpl;
import com.revature.organizations.dto.EmployeeDTO;
import com.revature.organizations.exceptions.DBException;
import com.revature.organizations.exceptions.ServiceException;
import com.revature.organizations.exceptions.ValidatorException;
import com.revature.organizations.validator.EmployeeServiceValidation;

@Service
public class EmployeeService {
	
	private  EmployeeDAO employeeDAOImpl;
	
	private  EmployeeServiceValidation employeeServiceValidation;
	
	@Autowired
	public EmployeeService(EmployeeDAO employeeDAOImpl,EmployeeServiceValidation employeeServiceValidation) {
		this.employeeDAOImpl = employeeDAOImpl;
		this.employeeServiceValidation=employeeServiceValidation;
	}


	

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
	public Boolean addEmployeeService(EmployeeDTO employeeDetailsDTO) throws ServiceException {
		LOGGER.info("Entered into Employee Service");
		Boolean isStatus = false;
		try {
			//employeeServiceValidation.employeeService(employeeDetailsDTO);

			isStatus = employeeDAOImpl.addEmployee(employeeDetailsDTO);
		} catch (DBException e) {
			LOGGER.error("Exception", e);
			throw new ServiceException(e.getMessage(),e);
		}
		return isStatus;

	}

}
