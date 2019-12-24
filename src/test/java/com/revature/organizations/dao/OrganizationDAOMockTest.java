
package com.revature.organizations.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.organizations.dto.OrganizationDTO;
import com.revature.organizations.exceptions.DBException;
import com.revature.organizations.model.ActivationCode;
import com.revature.organizations.model.Branding;
import com.revature.organizations.model.Domain;
import com.revature.organizations.model.OrganizationDetails;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationDAOMockTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationDAOMockTest.class);

	@InjectMocks
	OrganizationDAOImpl organizationDAO;

	@Mock
	PreparedStatement pst;

	@Mock
	DataSource con;

	@Mock
	Connection connection;

	@Mock
	ResultSet resultSet;

	@Before
	public void setUp() throws SQLException {
		when(con.getConnection()).thenReturn(connection);
		when(connection.prepareStatement(Mockito.anyString())).thenReturn(pst);
		when(pst.executeQuery()).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(Boolean.FALSE);

	}

	@Test
	public void getAll() throws DBException {
		List<OrganizationDetails> organizationDetails = null;
		organizationDetails = organizationDAO.getAll();

		assertNotNull(organizationDetails);

	}

	@Test
	public void createOrganization() throws DBException {

		OrganizationDTO organizationDTO = new OrganizationDTO();
		OrganizationDetails organization = new OrganizationDetails();
		organization.setName("ANNA University chennai");
		organization.setAliasName("AUCNIC");
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
		code.setActivationCodes(94881);
		long date = 2019 - 12 - 06L;
		Date fromDate = new Date(date);
		code.setFromDate(fromDate);
		long toDate = 2019 - 12 - 12L;
		Date toDateA = new Date(toDate);
		code.setToDate(toDateA);
		code.setCreatedBy(1);

		code.setActivationCodes(45791);
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
		orgDomain.setDomains("www.googleDomain1.in");
		orgDomain.setCreatedBy(1);
		domain.add(orgDomain);
		organizationDTO.setDomain(domain);

		Branding branding = new Branding();
		branding.setCreatedBy(1);
		branding.setFavicon("www.googlefavicon1.in");
		branding.setLogo("www.googleLogo1.in");
		organizationDTO.setBranding(branding);
		Boolean isStatus = organizationDAO.createOrganization(organizationDTO);
		LOGGER.info("test status" + organizationDTO);

		assertTrue(isStatus);

	}

	@Test(expected = DBException.class)
	public void

			createOrganizationInvalidTest() throws DBException {
		OrganizationDTO organizationDTO = new OrganizationDTO();
		OrganizationDetails organization = new OrganizationDetails();
		organization.setName("ANNA University chennai");
		organization.setAliasName("AUCNI");
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
		code.setActivationCodes(9488);
		long date = 2019 - 12 - 06L;
		Date fromDate = new Date(date);
		code.setFromDate(fromDate);
		long toDate = 2019 - 12 - 12L;
		Date toDateA = new Date(toDate);
		code.setToDate(toDateA);
		code.setCreatedBy(1);

		code.setActivationCodes(4579);
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
		orgDomain.setDomains("www.googleDomain.in");
		orgDomain.setCreatedBy(1);
		domain.add(orgDomain);
		organizationDTO.setDomain(domain);

		Branding branding = new Branding();
		branding.setCreatedBy(1);
		branding.setFavicon("www.googlefavicon.in");
		branding.setLogo("www.googleLogo.in");
		organizationDTO.setBranding(branding);
		organizationDAO.createOrganization(organizationDTO);

	}

}
