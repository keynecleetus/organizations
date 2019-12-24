package com.revature.organizations.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.organizations.dto.EmployeeDTO;
import com.revature.organizations.dto.Message;
import com.revature.organizations.exceptions.ServiceException;

import com.revature.organizations.model.OrganizationDetails;
import com.revature.organizations.service.EmployeeService;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

	@PostMapping
	@ApiOperation(value = "ADD Employee API")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Employee added Successfully", response = OrganizationDetails.class),
			@ApiResponse(code = 400, message = "Employee cannot be added", response = Message.class) })
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employeeDetails) {
		try {
			Boolean isStatus = false;
			isStatus = employeeService.addEmployeeService(employeeDetails);
			return new ResponseEntity<>(isStatus, HttpStatus.CREATED);

		} catch (ServiceException e) {
			LOGGER.error("ServiceException", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}

}
