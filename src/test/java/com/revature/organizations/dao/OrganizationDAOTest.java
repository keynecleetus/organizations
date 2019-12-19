
package com.revature.organizations.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.organizations.dto.OrganizationDTO;
import com.revature.organizations.exceptions.DBException;
import com.revature.organizations.model.ActivationCode;
import com.revature.organizations.model.Branding;
import com.revature.organizations.model.Domain;
import com.revature.organizations.model.OrganizationDetails;

public class OrganizationDAOTest {

	OrganizationDAOImpl organizationDAOImpl = new OrganizationDAOImpl();

	@Test
	public void listAll() throws DBException {
		List<OrganizationDetails> organizationDetails = null;

		organizationDetails = organizationDAOImpl.getAll();

		assertNotNull(organizationDetails);

	}

	private static OrganizationDTO organizationObject() {
		OrganizationDTO organizationDTO = new OrganizationDTO();
		OrganizationDetails organization = new OrganizationDetails();
		organization.setName("ANNA University chennai");
		organization.setAliasName("AUCN");
		organization.setSalesforceId("AU2019CH");
		organization.setType("Academic");
		organization.setEnableUserVerification(true);
		organization.setSignUpPage(true);
		organization.setCreatedBy(1);
		organization.setThemeId(2);
		organization.setCreatedBy(1);
		organizationDTO.setOrganization(organization);

		List<ActivationCode> activationCode = new ArrayList<ActivationCode>();
		ActivationCode code = new ActivationCode();
		code.setActivationCodes(432567);
		long date = 2019 - 12 - 06L;
		Date fromDate = new Date(date);
		code.setFromDate(fromDate);
		long toDate = 2019 - 12 - 12L;
		Date toDateA = new Date(toDate);
		code.setToDate(toDateA);
		code.setCreatedBy(1);
		activationCode.add(code);
		organizationDTO.setActivationCodes(activationCode);

		code.setActivationCodes(432567);
		long fdate = 2019 - 12 - 06L;
		Date fmdate = new Date(fdate);
		code.setFromDate(fmdate);
		long tDate = 2019 - 12 - 12L;
		Date tDteA = new Date(tDate);
		code.setToDate(tDteA);
		code.setCreatedBy(1);

		List<Domain> domain = new ArrayList<Domain>();
		Domain orgDomain = new Domain();
		orgDomain.setDomains("http://google.com");
		orgDomain.setCreatedBy(1);
		domain.add(orgDomain);
		organizationDTO.setDomain(domain);

		Branding branding = new Branding();
		branding.setCreatedBy(1);
		branding.setFavicon("http://google.com");
		branding.setLogo("http://google.com");
		organizationDTO.setBranding(branding);
		return organizationDTO;
	}

	@Test
	public void createOrganizationValidTest() throws DBException

	{
		Boolean isStatus = null;
		OrganizationDTO organizationDTO = organizationObject();
		isStatus = organizationDAOImpl.createOrganization(organizationDTO);
		assertTrue(isStatus);
	}

	@Test(expected = DBException.class)
	public void createOrganizationInValidTest() throws DBException {
		Boolean isStatus = null;

		OrganizationDTO organizationDTO = organizationObject();

		isStatus = organizationDAOImpl.createOrganization(organizationDTO);
		assertTrue(isStatus);

	}

}
