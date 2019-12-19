
package com.revature.organizations.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.organizations.dao.EmployeeDAOImpl;
import com.revature.organizations.dto.EmployeeDTO;
import com.revature.organizations.exceptions.DBException;
import com.revature.organizations.exceptions.ServiceException;
import com.revature.organizations.model.ActivationCode;
import com.revature.organizations.model.EmployeeDetails;
import com.revature.organizations.model.EmployeeRole;

@RunWith(MockitoJUnitRunner.class) public class EmployeeServiceMockTest {
  
  private Logger LOGGER=LoggerFactory.getLogger(EmployeeService.class);
  
  @InjectMocks EmployeeService employeeService;
  
  @Mock EmployeeDAOImpl employeeDAO;
  
  @Test public void addEmployeeValidTest() throws ServiceException,DBException { 
	  Boolean isStatus = false; 
	  EmployeeDTO employeeDTO = new EmployeeDTO(); 
	  EmployeeDetails employee=new EmployeeDetails();
  employee.setFirstName("keyne");
  employee.setLastName("Welbon");
  employee.setCountry("India"); 
  employee.setTimezone("Asia/kolkata");
  employee.setTitle("Professor"); 
  employee.setMobileNo(9488447061L);
  employee.setSalesforceId("KYN11049809L");
  employee.setStatus(true);
  employee.setCreatedBy("Anitha");
  employee.setEmail("keynewelbon.loui@gmail.com");
  employeeDTO.setEmployee(employee);
  
  List<EmployeeRole> employeeRoles = new ArrayList<EmployeeRole>();
  EmployeeRole role = new EmployeeRole();
  
  role.setRoleId(1); role.setOrganizationId(26); role.setCreatedBy(1);
  employeeRoles.add(role); employeeDTO.setEmployeeRole(employeeRoles);
  
  when(employeeDAO.addEmployee(employeeDTO)).thenReturn(true); isStatus =
  employeeService.addEmployeeService(employeeDTO); LOGGER.info("status" +
  isStatus);
  
  assertTrue(isStatus); } 
  //@Test(expected=ServiceException.class) 
  public void addEmployeeInValidTest() throws ServiceException, DBException { 
	  Boolean isStatus = false;
	  EmployeeDTO employeeDTO = new EmployeeDTO();
  EmployeeDetails employee=new EmployeeDetails();
  employee.setFirstName("keyne"); employee.setLastName("Welbon");
  employee.setCountry("India"); employee.setTimezone("Asia/kolkata");
  employee.setTitle("Professor"); employee.setMobileNo(9488447061L);
  employee.setSalesforceId("KYN11049809L"); employee.setStatus(true);
  employee.setCreatedBy("Anitha");
  employee.setEmail("keynewelbon.loui@gmail.com");
  employeeDTO.setEmployee(employee);
  
  List<EmployeeRole> employeeRoles = new ArrayList<EmployeeRole>();
  EmployeeRole role = new EmployeeRole();
  
  role.setRoleId(1); role.setOrganizationId(26); role.setCreatedBy(1);
  employeeRoles.add(role); employeeDTO.setEmployeeRole(employeeRoles);
  
  when(employeeDAO.addEmployee(employeeDTO)).thenReturn(true); isStatus =
  employeeService.addEmployeeService(employeeDTO); LOGGER.info("status" +
  isStatus);
  
  assertTrue(isStatus); }
  
  
  }
