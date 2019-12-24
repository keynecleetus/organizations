package com.revature.organizations.dto;


import java.util.List;

import com.revature.organizations.model.ActivationCode;
import com.revature.organizations.model.Branding;
import com.revature.organizations.model.Domain;
import com.revature.organizations.model.OrganizationDetails;


import lombok.Data;

@Data
public class OrganizationDTO {
	
	private OrganizationDetails organization;
	
	private List<ActivationCode> activationCodes;
	
	private Branding branding;
	
	private List<Domain> domain;
	
}
