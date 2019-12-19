package com.revature.organizations.dao;

import java.util.List;

import com.revature.organizations.dto.ManageOrganizationDTO;
import com.revature.organizations.dto.OrganizationDTO;
import com.revature.organizations.exceptions.DBException;
import com.revature.organizations.model.OrganizationDetails;

public interface OrganizationDAO {
	
	Boolean createOrganization(OrganizationDTO organization)throws DBException;
	
	 List<OrganizationDetails> getAll() throws DBException;
	 
	 Boolean manageOrganization(ManageOrganizationDTO manageOrganizationDTO)throws DBException;
	 
	

}
