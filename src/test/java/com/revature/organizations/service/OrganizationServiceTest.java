
package com.revature.organizations.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import org.junit.Test;
import com.revature.organizations.dao.OrganizationDAOImpl;
import com.revature.organizations.dto.ManageOrganizationDTO;
import com.revature.organizations.dto.OrganizationDTO;
import com.revature.organizations.exceptions.ServiceException;
import com.revature.organizations.model.ActivationCode;
import com.revature.organizations.model.Branding;
import com.revature.organizations.model.Domain;
import com.revature.organizations.model.OrganizationDetails;

public class OrganizationServiceTest {

	OrganizationService org = new OrganizationService(new OrganizationDAOImpl());

	@Test
	public void listAll() throws ServiceException {
		List<OrganizationDetails> organizationDTO = null;
		organizationDTO = org.getAll();

		assertNotNull(organizationDTO);
	}

	@Test(expected = ServiceException.class)
	public void createOrganizationInValidTest() throws ServiceException {
		Boolean isStatus = null;

		OrganizationDTO organizationDTO = organizationObject();

		isStatus = org.createOrganization(organizationDTO);
		assertTrue(isStatus);

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
	public void createOrganizationValidTest() throws ServiceException {
		Boolean isStatus = null;
		OrganizationDTO organizationDTO = organizationObject();
		isStatus = org.createOrganization(organizationDTO);

		assertTrue(isStatus);
	}

	@Test(expected = ServiceException.class)
	public void manageOrganizationInValidTest() throws ServiceException {

		ManageOrganizationDTO organizationDTO = new ManageOrganizationDTO();
		OrganizationDetails organization = new OrganizationDetails();
		organization.setName("ANNA Palgalaikazhagam");
		organization.setEnableUserVerification(true);
		organization.setSignUpPage(true);
		organization.setStatus(false);
		organization.setId(26);
		organization.setModifiedBy(1);
		organizationDTO.setOrganizationDetails(organization);

		List<ActivationCode> activationCode = new ArrayList<ActivationCode>();
		ActivationCode code = new ActivationCode();
		long date = 2019 - 12 - 06L;
		Date fromDate = new Date(date);
		code.setFromDate(fromDate);
		long toDate = 2019 - 12 - 12L;
		Date toDateA = new Date(toDate);
		code.setToDate(toDateA);
		code.setModifiedBy(1);
		code.setOrganizationId(26);
		activationCode.add(code);
		organizationDTO.setActivationCode(code);

		List<Domain> domain = new ArrayList<Domain>();
		Domain orgDomain = new Domain();
		orgDomain.setDomains("www.yopmail.com");
		orgDomain.setModifiedBy(1);
		orgDomain.setOrganizationId(26);
		domain.add(orgDomain);
		organizationDTO.setDomain(orgDomain);
		org.manageOrganization(organizationDTO);

	}

	@Test
	public void manageOrganizationValidTest() throws ServiceException {
		Boolean isStatus = null;
		ManageOrganizationDTO organizationDTO = manageOrganizationDTO();

		isStatus = org.manageOrganization(organizationDTO);

		assertTrue(isStatus);
	}

	private ManageOrganizationDTO manageOrganizationDTO() {
		ManageOrganizationDTO organizationDTO = new ManageOrganizationDTO();
		OrganizationDetails organization = new OrganizationDetails();
		organization.setName("ANNA Palgalakazhagam");
		organization.setEnableUserVerification(true);
		organization.setSignUpPage(true);
		organization.setStatus(false);
		organization.setId(25);
		organization.setModifiedBy(1);
		organizationDTO.setOrganizationDetails(organization);

		List<ActivationCode> activationCode = new ArrayList<ActivationCode>();
		ActivationCode code = new ActivationCode();
		long date = 2019 - 12 - 06L;
		Date fromDate = new Date(date);
		code.setFromDate(fromDate);
		long toDate = 2019 - 12 - 12L;
		Date toDateA = new Date(toDate);
		code.setToDate(toDateA);
		code.setModifiedBy(1);
		code.setOrganizationId(25);
		activationCode.add(code);
		organizationDTO.setActivationCode(code);

		List<Domain> domain = new ArrayList<Domain>();
		Domain orgDomain = new Domain();
		orgDomain.setDomains("www.yopmail.com");
		orgDomain.setModifiedBy(1);
		orgDomain.setOrganizationId(26);
		domain.add(orgDomain);
		organizationDTO.setDomain(orgDomain);
		return organizationDTO;
	}

}
