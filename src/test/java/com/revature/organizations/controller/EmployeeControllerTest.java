package com.revature.organizations.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.organizations.dto.EmployeeDTO;
import com.revature.organizations.model.EmployeeDetails;
import com.revature.organizations.model.EmployeeRole;
import com.revature.organizations.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
class EmployeeControllerTest {
	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	EmployeeController employeeController;
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp()
	{
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
				.apply(documentationConfiguration(this.restDocumentation).uris().withScheme("http")
						.withHost("localhost").withPort(9000))
				.build();
		objectMapper = new ObjectMapper();
	}
	

	@Test
	void getAddEmployee() throws Exception {
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
	  
	  role.setRoleId(1); 
	  role.setOrganizationId(26); 
	  role.setCreatedBy(1);
	  employeeRoles.add(role); 
	  employeeDTO.setEmployeeRole(employeeRoles);
		String orgJson = objectMapper.writeValueAsString(employeeDTO);
		when(employeeService.addEmployeeService(employeeDTO)).thenReturn(true);
		this.mockMvc.perform(post("/organization/").contentType(MediaType.APPLICATION_JSON).content(orgJson))
		.andExpect(status().isCreated()).andDo(print())
		.andDo(document("employee",preprocessRequest(prettyPrint()),preprocessResponse(prettyPrint())));
		
		
	}

}
