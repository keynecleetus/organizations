package com.revature.organizations.validator;



import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.revature.organizations.dto.MessageConstant;
import com.revature.organizations.dto.OrganizationDTO;
import com.revature.organizations.exceptions.ValidatorException;
import com.revature.organizations.model.Domain;

@Component
public class OrganizationServiceValidator {

	public void organizationService(OrganizationDTO organizationDTO) throws ValidatorException {
	String logo=organizationDTO.getBranding().getLogo();
	String favicon=organizationDTO.getBranding().getFavicon();
	List<Domain> domain=new ArrayList<>();
	Domain domains=new Domain();
	String orgDomain=domains.getDomains();
	domain.add(domains);
	
	Pattern url=Pattern.compile("(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?");
	if(logo==null||url.matcher(logo).matches())
	{
		throw new ValidatorException(MessageConstant.LOGO);
	}
	if(favicon==null||url.matcher(favicon).matches())
	{
		throw new ValidatorException(MessageConstant.FAVICON);
	}
	if(orgDomain==null||url.matcher(orgDomain).matches())
	{
		throw new ValidatorException(MessageConstant.DOMAIN);
	}
	
	Pattern firstName=Pattern.compile("[A-Z][a-zA-Z]*");
	String name=organizationDTO.getOrganization().getName();
	String aliasName=organizationDTO.getOrganization().getAliasName();
	String type=organizationDTO.getOrganization().getType();
	if (name == null|| firstName.matcher(name).matches()) {
		throw new ValidatorException(MessageConstant.NAME);
	}
	if (aliasName == null|| firstName.matcher(aliasName).matches()) {
		throw new ValidatorException(MessageConstant.ALIASNAME);
	}
	if (type == null|| firstName.matcher(type).matches()) {
		throw new ValidatorException(MessageConstant.TYPE);
	}
	Pattern salesForceId=Pattern.compile("[a-zA-Z0-9]{15}|[a-zA-Z0-9]{18}");
	String salesF=organizationDTO.getOrganization().getSalesforceId();
	if(salesF==null||salesForceId.matcher(salesF).matches()){
		throw new ValidatorException(MessageConstant.SALESFORCE);
	}
	
	

}
}
