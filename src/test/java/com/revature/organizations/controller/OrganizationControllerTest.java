package com.revature.organizations.controller;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.organizations.dto.ManageOrganizationDTO;
import com.revature.organizations.dto.OrganizationDTO;
import com.revature.organizations.model.ActivationCode;
import com.revature.organizations.model.Branding;
import com.revature.organizations.model.Domain;
import com.revature.organizations.model.OrganizationDetails;
import com.revature.organizations.service.OrganizationService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OrganizationControllerTest {
	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
	@InjectMocks
	OrganizationController organizationController;
	@Mock
	OrganizationService organizationService;
	private MockMvc mockMvc;
	private ObjectMapper objectmapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(organizationController)
				.apply(documentationConfiguration(this.restDocumentation).uris().withScheme("http")
						.withHost("localhost").withPort(9000))
				.build();
		objectmapper = new ObjectMapper();
	}

	@Test
	public void getAllOrganizations() throws Exception {
		List<OrganizationDetails> orgDetails = new ArrayList<OrganizationDetails>();
		OrganizationDetails organizations = new OrganizationDetails();
		organizations.setName("ANNA University");
		organizations.setType("academic");
		organizations.setAliasName("AUCN");
		organizations.setId(1);
		orgDetails.add(organizations);
		when(organizationService.getAll()).thenReturn(orgDetails);
		this.mockMvc.perform(get("/organization")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json")).andDo(document("list organization",
						preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	public void createOrganizations() throws Exception {

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
		organizationDTO.setOrganization(organization);

		List<ActivationCode> activationCode = new ArrayList<ActivationCode>();
		ActivationCode code = new ActivationCode();
		code.setActivationCodes(43256712);
		long date = 2019 - 12 - 17L;
		Date fromDate = new Date(date);
		code.setFromDate(fromDate);
		long toDate = 2019 - 12 - 23L;
		Date toDateA = new Date(toDate);
		code.setToDate(toDateA);
		code.setCreatedBy(1);
		activationCode.add(code);
		organizationDTO.setActivationCodes(activationCode);

		code.setActivationCodes(432567);
		long fdate = 2019 - 12 - 17L;
		Date fmdate = new Date(fdate);
		code.setFromDate(fmdate);
		long tDate = 2019 - 12 - 23L;
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
		String orgJson = objectmapper.writeValueAsString(organizationDTO);
		when(organizationService.createOrganization(organizationDTO)).thenReturn(true);
		this.mockMvc.perform(post("/organization/").contentType(MediaType.APPLICATION_JSON).content(orgJson))

				.andExpect(status().isCreated()).andDo(print())
				.andDo(document("organization", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
						requestFields(
								fieldWithPath("organization.id").description("Id of organization")
										.attributes(key("required").value(false)),
								fieldWithPath("organization.name").description("Name of organization")
										.attributes(key("required").value(true)),
								fieldWithPath("organization.aliasName").description("AliasName of the organization")
										.attributes(key("required").value(true)),
								fieldWithPath("organization.type").description("Type of the organization")
										.attributes(key("required").value(true)),
								fieldWithPath("organization.salesforceId").description("Sales force ID")
										.attributes(key("required").value(true)),
								fieldWithPath("organization.enableUserVerification").description("User verification")
										.attributes(key("required").value(true)),
								fieldWithPath("organization.signUpPage").description("Sign up Page")
										.attributes(key("required").value(true)),
								fieldWithPath("organization.themeId").description("Theme ID")
										.attributes(key("required").value(true)),
								fieldWithPath("organization.createdBy").description("Created By")
										.attributes(key("required").value(true)),
								fieldWithPath("organization.modifiedBy").description("modified By")
										.attributes(key("required").value(false)),
								fieldWithPath("activationCodes[].id").description("Id of Activation Code")
										.attributes(key("required").value(false)),
								fieldWithPath("activationCodes[].organizationId").description("Id of organization")
										.attributes(key("required").value(true)),
								fieldWithPath("activationCodes[].activationCodes")
										.description("activationCodes of organization")
										.attributes(key("required").value(true)),
								fieldWithPath("activationCodes[].fromDate").description("Start date of activationCodes")
										.attributes(key("required").value(true)),
								fieldWithPath("activationCodes[].toDate").description("End date of activationCodes")
										.attributes(key("required").value(true)),
								fieldWithPath("activationCodes[].createdBy").description("Created By")
										.attributes(key("required").value(true)),
								fieldWithPath("activationCodes[].modifiedBy").description("modified By")
										.attributes(key("required").value(false)),
								fieldWithPath("branding.id").description("Id of Branding")
										.attributes(key("required").value(false)),
								fieldWithPath("branding.organizationId").description("Id of organization")
										.attributes(key("required").value(true)),
								fieldWithPath(".branding.logo").description("logo of organization")
										.attributes(key("required").value(false)),
								fieldWithPath("branding.favicon").description("favicon of organization")
										.attributes(key("required").value(true)),
								fieldWithPath("branding.createdBy").description("Created By")
										.attributes(key("required").value(true)),
								fieldWithPath("branding.modifiedBy").description("modified By")
										.attributes(key("required").value(false)),
								fieldWithPath("domain[].id").description("Id of Activation Code")
										.attributes(key("required").value(false)),
								fieldWithPath("domain[].organizationId").description("Id of organization")
										.attributes(key("required").value(true)),
								fieldWithPath("domain[].domains").description("domain of the organization")
										.attributes(key("required").value(true)),
								fieldWithPath("domain[].createdBy").description("Created By")
										.attributes(key("required").value(true)),
								fieldWithPath("domain[].modifiedBy").description("modified By")
										.attributes(key("required").value(false)))));
	}

	@Test
	public void manageOrganizationValidTest() throws Exception {

		ManageOrganizationDTO organizationDTO = manageOrganizationDTO();

		String orgJson = objectmapper.writeValueAsString(organizationDTO);
		when(organizationService.manageOrganization(organizationDTO)).thenReturn(true);
		this.mockMvc.perform(put("/organization/").contentType(MediaType.APPLICATION_JSON).content(orgJson))
				.andExpect(status().isOk()).andExpect(content().string("organization updated successfully"))
				.andDo(document("manage organization", preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint()),
						requestFields(
								fieldWithPath("organizationDetails.id").description("Id of organization")
										.attributes(key("required").value(false)),
								fieldWithPath("organizationDetails.name").description("Name of organization")
										.attributes(key("required").value(true)),
								fieldWithPath("organizationDetails.aliasName")
										.description("AliasName of the organization")
										.attributes(key("required").value(true)),
								fieldWithPath("organizationDetails.type").description("Type of the organization")
										.attributes(key("required").value(true)),
								fieldWithPath("organizationDetails.salesforceId").description("Sales force ID")
										.attributes(key("required").value(true)),
								fieldWithPath("organizationDetails.enableUserVerification")
										.description("User verification").attributes(key("required").value(true)),
								fieldWithPath("organizationDetails.signUpPage").description("Sign up Page")
										.attributes(key("required").value(true)),
								fieldWithPath("organizationDetails.themeId").description("Theme ID")
										.attributes(key("required").value(true)),
								fieldWithPath("organizationDetails.createdBy").description("Created By")
										.attributes(key("required").value(true)),
								fieldWithPath("organizationDetails.modifiedBy").description("modified By")
										.attributes(key("required").value(false)),
								fieldWithPath("activationCode.id").description("Id of Activation Code")
										.attributes(key("required").value(false)),
								fieldWithPath("activationCode.organizationId").description("Id of organization")
										.attributes(key("required").value(true)),
								fieldWithPath("activationCode.activationCodes")
										.description("activationCodes of organization")
										.attributes(key("required").value(true)),
								fieldWithPath("activationCode.fromDate").description("Start date of activationCodes")
										.attributes(key("required").value(true)),
								fieldWithPath("activationCode.toDate").description("End date of activationCodes")
										.attributes(key("required").value(true)),
								fieldWithPath("activationCode.createdBy").description("Created By")
										.attributes(key("required").value(true)),
								fieldWithPath("activationCode.modifiedBy").description("modified By")
										.attributes(key("required").value(false)),
								fieldWithPath("domain.id").description("Id of Domain")
										.attributes(key("required").value(false)),
								fieldWithPath("domain.organizationId").description("Id of organization")
										.attributes(key("required").value(true)),
								fieldWithPath("domain.domains").description("domain of the organization")
										.attributes(key("required").value(true)),
								fieldWithPath("domain.createdBy").description("Created By")
										.attributes(key("required").value(false)),
								fieldWithPath("domain.modifiedBy").description("modified By")
										.attributes(key("required").value(true)))));

	}

	private ManageOrganizationDTO manageOrganizationDTO() {
		ManageOrganizationDTO organizationDTO = new ManageOrganizationDTO();
		OrganizationDetails organization = new OrganizationDetails();
		organization.setName("ANNA Palgalaikazhagam");
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

	@Test
	public void doRemoveTest() throws Exception {
		when(organizationService.deleteLogo(Mockito.anyInt())).thenReturn(true);
		this.mockMvc.perform(delete("/organization/?organizationId=5")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andDo(document("delete logo and favicon", preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint()), requestParameters(parameterWithName("organizationId")
								.description("organization to be deleted").attributes(key("required").value(true)))));
	}

}
