package com.revature.organizations.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.revature.organizations.dto.EmployeeDTO;
import com.revature.organizations.dto.MessageConstant;
import com.revature.organizations.exceptions.ValidatorException;
import com.revature.organizations.model.EmployeeRole;

@Component
public class EmployeeServiceValidation {
	public void employeeService(EmployeeDTO employee) throws ValidatorException {
		
		
		List<EmployeeRole> employeeRole=new ArrayList<EmployeeRole>();
		for(EmployeeRole employees:employeeRole)
		{
	  int roleId = employees.getRoleId(); 
	  long createdById =employees.getCreatedBy();
	  int organizationId = employees.getOrganizationId();
	  Pattern idPattern = Pattern.compile(".*[^0-9].*");
	  
	  if (roleId == 0 ||idPattern.matcher(String.valueOf(roleId)).matches()) { 
		  throw new ValidatorException(MessageConstant.ROLE_ID);
		  } 
	  if (createdById == 0 || idPattern.matcher(String.valueOf(createdById)).matches()) { 
		  throw new ValidatorException(MessageConstant.CREATED_BY_ID); 
		  }
	  if (organizationId == 0 || idPattern.matcher(String.valueOf(organizationId)).matches()) {
		  throw new ValidatorException(MessageConstant.ORGANIZATION_ID);
		  }
		}
	  long mobileNo = employee.getEmployee().getMobileNo();
	  String mobile = Long.toString(mobileNo);
	  Pattern validateMobileNo = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 
	   if (mobileNo == 0 ||validateMobileNo.matcher(mobile).matches()) {
		   throw new ValidatorException(MessageConstant.MOBILENO); 
		   }
	  Pattern firstName =Pattern.compile("[a-zA-Z]");
	  String fName = employee.getEmployee().getFirstName();
	  if (fName == null || firstName.matcher(fName).matches()) 
	  { 
		  throw new  ValidatorException(MessageConstant.FIRSTNAME); 
		  } 
	  String lName =  employee.getEmployee().getLastName(); 
	  if (lName == null ||firstName.matcher(lName).matches())
	  {
		  throw new  ValidatorException(MessageConstant.LASTNAME);
		  } 
	  Pattern  salesForceId=Pattern.compile("[a-zA-Z0-9]{15}|[a-zA-Z0-9]{18}"); 
	  String salesF=employee.getEmployee().getSalesforceId();
	  if(salesF==null||salesForceId.matcher(salesF).matches())
	  { 
		  throw new ValidatorException(MessageConstant.SALESFORCE); 
		  }
	   }

	
} 
	  
	 

