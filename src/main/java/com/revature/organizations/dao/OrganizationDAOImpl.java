package com.revature.organizations.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.organizations.dto.ManageOrganizationDTO;
import com.revature.organizations.dto.MessageConstant;
import com.revature.organizations.dto.OrganizationDTO;
import com.revature.organizations.exceptions.DBException;
import com.revature.organizations.model.ActivationCode;
import com.revature.organizations.model.Domain;
import com.revature.organizations.model.OrganizationDetails;
import com.revature.organizations.util.ConnectionUtil;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

	 @Autowired
	 private DataSource datasource;

	private static final String SQL_EXCEPTION = "SQLException";
	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationDAOImpl.class);

	public Boolean createOrganization(OrganizationDTO organizationDTO) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Boolean isStatus = false;
		Savepoint createOrganization = null;
		Date date = new Date();
		Timestamp currentTime = new Timestamp(date.getTime());
		try {
			con = datasource.getConnection();
			con.setAutoCommit(false);
			createOrganization = con.setSavepoint("SAVEPOINT");
			// Creating organization
			String organization = "insert into organizations(name,alias_name,type,salesforce_id,"
					+ "enable_user_verification,sign_up_page,theme_id,created_by,created_date)values(?,?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(organization);

			pst.setString(1, organizationDTO.getOrganization().getName());
			pst.setString(2, organizationDTO.getOrganization().getAliasName());
			pst.setString(3, organizationDTO.getOrganization().getType());
			pst.setString(4, organizationDTO.getOrganization().getSalesforceId());
			pst.setBoolean(5, organizationDTO.getOrganization().isEnableUserVerification());
			pst.setBoolean(6, organizationDTO.getOrganization().isSignUpPage());
			pst.setInt(7, organizationDTO.getOrganization().getThemeId());
			pst.setLong(8, organizationDTO.getOrganization().getCreatedBy());
			pst.setTimestamp(9, currentTime);

			int createOrganizationStatus = pst.executeUpdate();
			LOGGER.info("dao createActivationCode"+createOrganizationStatus);
			pst.close();
	

			String id = "select last_insert_id()";
			pst = con.prepareStatement(id);

			rs = pst.executeQuery();
			int organizationId = 0;
			if (rs.next()) {
				organizationId = rs.getInt("last_insert_id()");
			}
			// Adding activation Code
			if (organizationDTO.getActivationCodes() != null) {
				List<ActivationCode> code = organizationDTO.getActivationCodes();
				String activationCodes = "";
				int codeCount = 0;
				for (ActivationCode activationCode : code) {
					activationCodes = activationCodes + "(" + activationCode.getActivationCodes() + "," + "\""
							+ activationCode.getFromDate() + "\"" + "," + "\"" + activationCode.getToDate() + "\"" + ","
							+ activationCode.getCreatedBy() + "," + organizationId + "," + "\"" + currentTime + "\""
							+ ")";
					codeCount++;
					if (codeCount < code.size()) {
						activationCodes += ",";
					}

				}
				String activationcode = "insert into organization_activation_code(activation_code,"
						+ "from_date,to_date,created_by,organization_id,created_date)values" + activationCodes;
				pst = con.prepareStatement(activationcode);
				System.out.println("query" + activationcode);
				int createActivationCode=pst.executeUpdate();
				LOGGER.info("dao createActivationCode"+createActivationCode);
				
			}
			// Adding domain
			if (organizationDTO.getDomain() != null) {
				List<Domain> domain = organizationDTO.getDomain();
				String organizationDomains = "";
				int domainCount = 0;
				for (Domain domains : domain) {
					organizationDomains = organizationDomains + "(" + "\"" + domains.getDomains() + "\"" + ","
							+ domains.getCreatedBy() + "," + organizationId + "," + "\"" + currentTime + "\"" + ")";
					domainCount++;
					if (domainCount < domain.size()) {
						organizationDomains += ",";
					}
				}
				String domains = "insert into organization_domain(domains,created_by,organization_id,created_date)values"
						+ organizationDomains;
				System.out.println("domain" + domains);
				pst = con.prepareStatement(domains);
				
			}
			int createDomainCode=pst.executeUpdate();
			LOGGER.info("dao createDomainCode"+createDomainCode);
			// Adding Branding
			if (organizationDTO.getBranding() != null) {
				String branding = "insert into organization_branding(organization_id,"
						+ "logo,favicon,created_by,created_date)values(?,?,?,?,?)";
				pst = con.prepareStatement(branding);
				pst.setInt(1, organizationId);
				pst.setString(2, organizationDTO.getBranding().getLogo());
				pst.setString(3, organizationDTO.getBranding().getFavicon());
				pst.setLong(4, organizationDTO.getBranding().getCreatedBy());
				pst.setTimestamp(5, currentTime);
				
				int createBranding=pst.executeUpdate();
			}
			
			LOGGER.info("dao status"+isStatus);
			con.commit();

		} catch (SQLException e) {
			LOGGER.error(SQL_EXCEPTION, e);
			try {
				con.rollback(createOrganization);
			} catch (SQLException e1) {
				LOGGER.error(SQL_EXCEPTION, e);

			}
			throw new DBException(MessageConstant.CREATE_ORGANIZATION);

		} finally {
			try {
				con.close();
				pst.close();
			} catch (SQLException e) {
				LOGGER.error(SQL_EXCEPTION, e);
			}
		}

		return isStatus;

	}

	public List<OrganizationDetails> getAll() throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		OrganizationDetails organizationDTO = null;
		List<OrganizationDetails> list = null;
		try {
			con = datasource.getConnection();
			String sql = "select name,alias_name,type,status from organizations";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			list = new ArrayList<>();
			while (rs.next()) {

				organizationDTO = new OrganizationDetails();

				organizationDTO = toRow(rs);
				list.add(organizationDTO);

			}

		} catch (SQLException e) {
			LOGGER.error(SQL_EXCEPTION, e);
			throw new DBException(MessageConstant.ORGANIZATION_LIST);
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				LOGGER.error(SQL_EXCEPTION, e);
			}
		}
		return list;
	}

	private OrganizationDetails toRow(ResultSet rs) throws DBException {
		OrganizationDetails organizationDTO = null;
		try {

			String name = rs.getString("name");

			String aliasName = rs.getString("alias_name");
			String type = rs.getString("type");
			Boolean status = rs.getBoolean("status");
			organizationDTO = new OrganizationDetails();

			organizationDTO.setName(name);
			organizationDTO.setAliasName(aliasName);
			organizationDTO.setType(type);
			organizationDTO.setStatus(status);

		} catch (SQLException e) {
			LOGGER.error(SQL_EXCEPTION, e);
			throw new DBException(MessageConstant.ORGANIZATION_LIST);
		}
		return organizationDTO;

	}

	public Boolean manageOrganization(ManageOrganizationDTO manageorganization) throws DBException {
		Boolean isUpdate = false;
		Connection con = null;
		PreparedStatement pst = null;
		Savepoint manageOrganizations = null;

		try {
			con = datasource.getConnection();
			con.setAutoCommit(false);
			manageOrganizations = con.setSavepoint("SAVEPOINT");
			String manageOrganization = "update organizations set name=?,enable_user_verification=?,sign_up_page=?,modified_by=?,status=? where id=?";
			pst = con.prepareStatement(manageOrganization);
			pst.setString(1, manageorganization.getOrganizationDetails().getName());
			pst.setBoolean(2, manageorganization.getOrganizationDetails().isEnableUserVerification());
			pst.setBoolean(3, manageorganization.getOrganizationDetails().isSignUpPage());
			pst.setLong(4, manageorganization.getOrganizationDetails().getModifiedBy());
			pst.setBoolean(5, manageorganization.getOrganizationDetails().isStatus());
			pst.setInt(6, manageorganization.getOrganizationDetails().getId());
			int update = pst.executeUpdate();

			pst.close();

			String updateActivationCode = "update organization_activation_code set from_date=?,to_date=?,modified_by=? where organization_id=?";
			pst = con.prepareStatement(updateActivationCode);
			pst.setDate(1, manageorganization.getActivationCode().getFromDate());
			pst.setDate(2, manageorganization.getActivationCode().getToDate());
			pst.setLong(3, manageorganization.getActivationCode().getModifiedBy());
			pst.setInt(4, manageorganization.getActivationCode().getOrganizationId());
			int updateCode = pst.executeUpdate();

			String updateDomain = "update organization_domain set domains=? where organization_id=?";
			pst = con.prepareStatement(updateDomain);
			pst.setString(1, manageorganization.getDomain().getDomains());
			pst.setInt(2, manageorganization.getDomain().getOrganizationId());
			int updateOrgDomain = pst.executeUpdate();
			if (updateOrgDomain == 1 && updateCode == 1 && update == 1) {
				isUpdate = true;
			}
			con.commit();
		} catch (SQLException e) {
			LOGGER.error(SQL_EXCEPTION, e);
			try {
				con.rollback(manageOrganizations);
			} catch (SQLException e1) {
				LOGGER.error(SQL_EXCEPTION, e1);
			}
			throw new DBException(MessageConstant.UNABLE_TO_UPDATE_THE_ORGANIZATION);
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				LOGGER.error(SQL_EXCEPTION, e);
			}

		}
		LOGGER.info("dao" + isUpdate);
		return isUpdate;

	}

	public Boolean deleteLogo(int organizationId)throws DBException {
		
		Boolean result = false;
		Connection con = null;
		PreparedStatement pst = null;
		Savepoint deleteFaviconn = null;
		try {
			con = datasource.getConnection();
			con.setAutoCommit(false);
			deleteFaviconn = con.setSavepoint("savepoint");
			String sql = "delete from organization_branding where organization_id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, organizationId);
			int check = pst.executeUpdate();
			if (check == 1) {
				result = true;
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback(deleteFaviconn);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new DBException("Unable to delete Logo and Favicon");
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("result" + result);
		return result;
	}
}
