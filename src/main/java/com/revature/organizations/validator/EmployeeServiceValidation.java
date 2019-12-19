package com.revature.organizations.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.revature.organizations.dto.EmployeeDTO;
import com.revature.organizations.dto.MessageConstant;
import com.revature.organizations.exceptions.ValidatorException;

@Component
public class EmployeeServiceValidation {

	/*
	 * public void employeeService(EmployeeDTO employee) throws ValidatorException {
	 * int roleId = employee.getRoleId(); int createdById =
	 * employee.getCreatedByID(); int organizationId = employee.getOrganizationId();
	 * Pattern idPattern = Pattern.compile(".*[^0-9].*"); long mobileNo =
	 * employee.getMobileNo(); if (roleId == 0 ||
	 * idPattern.matcher(String.valueOf(roleId)).matches()) { throw new
	 * ValidatorException(MessageConstant.ROLE_ID); } if (createdById == 0 ||
	 * idPattern.matcher(String.valueOf(createdById)).matches()) { throw new
	 * ValidatorException(MessageConstant.CREATED_BY_ID); } if (organizationId == 0
	 * || idPattern.matcher(String.valueOf(organizationId)).matches()) { throw new
	 * ValidatorException(MessageConstant.ORGANIZATION_ID); }
	 * 
	 * // String mobile = Long.toString(mobileNo); // Pattern validateMobileNo =
	 * Pattern.compile("(0/91)?[7-9][0-9]{9}"); // if (mobileNo == 0 ||
	 * validateMobileNo.matcher(mobile).matches()) { // throw new
	 * ValidatorException(MessageConstant.MOBILENO); // } Pattern firstName =
	 * Pattern.compile("[a-zA-Z]"); String fName = employee.getFirstName(); if
	 * (fName == null || firstName.matcher(fName).matches()) { throw new
	 * ValidatorException(MessageConstant.FIRSTNAME); } Pattern lastName =
	 * Pattern.compile(".*[a-zA-z]+([ '-][a-zA-Z]+).*"); String LName =
	 * employee.getFirstName(); if (LName == null ||
	 * lastName.matcher(LName).matches()) { throw new
	 * ValidatorException(MessageConstant.LASTNAME); } Pattern
	 * salesForceId=Pattern.compile("[a-zA-Z0-9]{15}|[a-zA-Z0-9]{18}"); String
	 * salesF=employee.getSalesforceId();
	 * if(salesF==null||salesForceId.matcher(salesF).matches()){ throw new
	 * ValidatorException(MessageConstant.SALESFORCE); }
	 * 
	 * }
	 */
}
