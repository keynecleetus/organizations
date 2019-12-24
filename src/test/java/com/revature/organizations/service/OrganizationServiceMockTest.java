
package com.revature.organizations.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.revature.organizations.dao.OrganizationDAOImpl;
import com.revature.organizations.dto.ManageOrganizationDTO;
import com.revature.organizations.dto.OrganizationDTO;
import com.revature.organizations.exceptions.DBException;
import com.revature.organizations.exceptions.ServiceException;
import com.revature.organizations.model.ActivationCode;
import com.revature.organizations.model.Branding;
import com.revature.organizations.model.Domain;
import com.revature.organizations.model.OrganizationDetails;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationServiceMockTest {

	@InjectMocks
	OrganizationService organizationService;

	@Mock
	OrganizationDAOImpl organizationDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationService.class);

	@Test
	public void getAllOrganizationTest() {
		List<OrganizationDetails> organizationDetails = new ArrayList<OrganizationDetails>();
		OrganizationDetails orgDetails = new OrganizationDetails();
		orgDetails.setName("ANNA");
		orgDetails.setAliasName("AUC");
		organizationDetails.add(orgDetails);

		try {
			when(organizationDAO.getAll()).thenReturn(organizationDetails);
			List<OrganizationDetails> listOrg = organizationService.getAll();
			assertEquals(listOrg, organizationDetails);
			LOGGER.info("array list" + listOrg);
			LOGGER.info("org list" + organizationDetails);
		} catch (DBException | ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = ServiceException.class)
	public void getAllOrganizationInvalidTest() throws DBException, ServiceException {
		when(organizationDAO.getAll()).thenThrow(DBException.class);
		organizationService.getAll();
	}

	@Test
	public void createOrganizationValidTest() {
		Boolean isStatus = false;
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
		code.setActivationCodes(4325671);
		long date = 2019 - 12 - 06L;
		Date fromDate = new Date(date);
		code.setFromDate(fromDate);
		long toDate = 2019 - 12 - 12L;
		Date toDateA = new Date(toDate);
		code.setToDate(toDateA);
		code.setCreatedBy(1);

		code.setActivationCodes(4325671);
		long fdate = 2019 - 12 - 06L;
		Date fmdate = new Date(fdate);
		code.setFromDate(fmdate);
		long tDate = 2019 - 12 - 12L;
		Date tDteA = new Date(tDate);
		code.setToDate(tDteA);
		code.setCreatedBy(1);

		activationCode.add(code);
		organizationDTO.setActivationCodes(activationCode);

		List<Domain> domain = new ArrayList<Domain>();
		Domain orgDomain = new Domain();
		orgDomain.setDomains("www.google.in");
		orgDomain.setCreatedBy(1);
		domain.add(orgDomain);
		organizationDTO.setDomain(domain);

		Branding branding = new Branding();
		branding.setCreatedBy(1);
		branding.setFavicon("www.google.in");
		branding.setLogo("www.google.in");
		organizationDTO.setBranding(branding);

		try {
			when(organizationDAO.createOrganization(organizationDTO)).thenReturn(true);
			isStatus = organizationService.createOrganization(organizationDTO);
		} catch (DBException | ServiceException e) {
			e.printStackTrace();
		}

		assertTrue(isStatus);

	}

	@Test(expected = ServiceException.class)
	public void createOrganizationInValidTest() throws DBException, ServiceException {

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
		code.setActivationCodes(4325671);
		long date = 2019 - 12 - 06L;
		Date fromDate = new Date(date);
		code.setFromDate(fromDate);
		long toDate = 2019 - 12 - 12L;
		Date toDateA = new Date(toDate);
		code.setToDate(toDateA);
		code.setCreatedBy(1);

		code.setActivationCodes(4325671);
		long fdate = 2019 - 12 - 06L;
		Date fmdate = new Date(fdate);
		code.setFromDate(fmdate);
		long tDate = 2019 - 12 - 12L;
		Date tDteA = new Date(tDate);
		code.setToDate(tDteA);
		code.setCreatedBy(1);

		activationCode.add(code);
		organizationDTO.setActivationCodes(activationCode);

		List<Domain> domain = new ArrayList<Domain>();
		Domain orgDomain = new Domain();
		orgDomain.setDomains("www.google.in");
		orgDomain.setCreatedBy(1);
		domain.add(orgDomain);
		organizationDTO.setDomain(domain);

		Branding branding = new Branding();
		branding.setCreatedBy(1);
		branding.setFavicon("www.google.in");
		branding.setLogo("www.google.in");
		organizationDTO.setBranding(branding);

		when(organizationDAO.createOrganization(organizationDTO)).thenThrow(DBException.class);
		organizationService.createOrganization(organizationDTO);

	}

	@Test
	public void manageOrganizationValidTest() {
		Boolean isStatus = null;
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
		orgDomain.setOrganizationId(25);
		domain.add(orgDomain);
		organizationDTO.setDomain(orgDomain);

		try {
			when(organizationDAO.manageOrganization(organizationDTO)).thenReturn(true);
			isStatus = organizationService.manageOrganization(organizationDTO);

		} catch (DBException | ServiceException e) {
			e.printStackTrace();
		}

		assertTrue(isStatus);
	}

	@Test(expected = ServiceException.class)
	public void manageOrganizationInValidTest() throws DBException, ServiceException {
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
		orgDomain.setOrganizationId(25);
		domain.add(orgDomain);
		organizationDTO.setDomain(orgDomain);

		when(organizationDAO.manageOrganization(organizationDTO)).thenThrow(DBException.class);
		organizationService.manageOrganization(organizationDTO);

	}

}
