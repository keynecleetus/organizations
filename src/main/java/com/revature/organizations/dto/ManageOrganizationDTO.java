package com.revature.organizations.dto;

import com.revature.organizations.model.ActivationCode;
import com.revature.organizations.model.Domain;
import com.revature.organizations.model.OrganizationDetails;

import lombok.Data;

@Data
public class ManageOrganizationDTO {
	
	public OrganizationDetails organizationDetails;
	
	public ActivationCode activationCode;
	
	public Domain domain;

}
