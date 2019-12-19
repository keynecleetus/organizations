package com.revature.organizations.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.organizations.dto.EmployeeDTO;
import com.revature.organizations.dto.MessageConstant;
import com.revature.organizations.exceptions.DBException;
import com.revature.organizations.model.EmployeeRole;


@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	@Autowired
	private DataSource datasource;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationDAOImpl.class);

	public Boolean addEmployee(EmployeeDTO employeeDTO) throws DBException {
		Connection con = null;
		PreparedStatement pst = null;
		Boolean isStatus = null;
		Savepoint createOrganization = null;
		ResultSet rs = null;
		try {
			con = datasource.getConnection();
			con.setAutoCommit(false);
			createOrganization = con.setSavepoint("SAVEPOINT");
			String addEmployee = "insert into employees(first_name,last_name"
					+ ",email,mobile_no,salesforce_id,title,timezone,country,created_by,created_date,status)values(?,?,?,?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(addEmployee);
			pst.setString(1, employeeDTO.getEmployee().getFirstName());
			pst.setString(2, employeeDTO.getEmployee().getLastName());
			pst.setString(3, employeeDTO.getEmployee().getEmail());
			pst.setLong(4, employeeDTO.getEmployee().getMobileNo());
			pst.setString(5, employeeDTO.getEmployee().getSalesforceId());
			pst.setString(6, employeeDTO.getEmployee().getTitle());
			pst.setString(7, employeeDTO.getEmployee().getTimezone());
			pst.setString(8, employeeDTO.getEmployee().getCountry());
			pst.setString(9, employeeDTO.getEmployee().getCreatedBy());
			Date date = new Date();
			Timestamp currentTime = new Timestamp(date.getTime());
			pst.setTimestamp(10, currentTime);
			pst.setBoolean(11, employeeDTO.getEmployee().isStatus());
			int addEmployees = pst.executeUpdate();

			pst.close();
			String id = "select last_insert_id()";
			pst = con.prepareStatement(id);

			rs = pst.executeQuery();
			int employeeId = 0;
			if (rs.next()) {
				employeeId = rs.getInt("last_insert_id()");
			}

			List<EmployeeRole> employeeRole = employeeDTO.getEmployeeRole();
			String employeeRoles = "";
			int roleCount = 0;
			for (EmployeeRole employee : employeeRole) {
				employeeRoles = employeeRoles + "(" + employee.getRoleId() + "," + employeeId + ","
						+ employee.getOrganizationId() + "," + employee.getCreatedBy() + "," + "\"" + currentTime + "\""
						+ ")";
				roleCount++;
				if (roleCount < employeeRole.size()) {
					employeeRoles += ",";
				}
			}
			String selectRole = "insert into employee_roles(role_id,employee_id,organization_id,created_by,created_date)values"
					+ employeeRoles;
			pst = con.prepareStatement(selectRole);
     		int selectRoles = pst.executeUpdate();

			con.commit();
			if (addEmployees == 1 && selectRoles == 1) {
				isStatus = true;
			}
			System.out.println("status in dao" + isStatus);

		} catch (SQLException e) {
			LOGGER.error("Exception", e);
			try {
				con.rollback(createOrganization);
			} catch (SQLException e1) {
				LOGGER.error("SQLException", e1);
			}
			throw new DBException(MessageConstant.UNABLE_TO_ADD_EMPLOYEE);

		} finally {
			try {
				con.close();
				pst.close();
			} catch (SQLException e) {
				LOGGER.error("SQLException", e);
				throw new DBException(MessageConstant.UNABLE_TO_CLOSE);
			}

		}

		return isStatus;

	}
}
