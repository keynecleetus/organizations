package com.revature.organizations.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.organizations.dto.ManageOrganizationDTO;
import com.revature.organizations.dto.Message;
import com.revature.organizations.dto.OrganizationDTO;
import com.revature.organizations.exceptions.ServiceException;
import com.revature.organizations.exceptions.ValidatorException;
import com.revature.organizations.model.OrganizationDetails;
import com.revature.organizations.service.OrganizationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("organization")
public class OrganizationController {

	private static final String SERVICE_EXCEPTION = "ServiceException";

	private OrganizationService organizationService;

	public OrganizationController(OrganizationService organizationService) {
		super();
		this.organizationService = organizationService;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

	@PostMapping
	@ApiOperation(value = "CreateOrganization API")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Organization created Successfully", response = OrganizationDetails.class),
			@ApiResponse(code = 400, message = "Organization cannot be created", response = Message.class),
			@ApiResponse(code = 500, message ="internal server error", response = Message.class),
			})
	public ResponseEntity<?> create(@RequestBody OrganizationDTO organizationDetails) {

		try {
			organizationService.createOrganization(organizationDetails);
			return new ResponseEntity<>("organization created successfully", HttpStatus.CREATED);

		} catch (ServiceException e) {
			LOGGER.error("ServiceException", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (ValidatorException e) {
			LOGGER.error("ValidatorException", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping()
	@ApiOperation(value = "list Organization API")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Organization list", response = OrganizationDetails.class),
			@ApiResponse(code = 500, message = "Organization cannot be listed", response = Message.class) })

	public ResponseEntity<?> list() {

		List<OrganizationDetails> list = null;

		try {
			list = organizationService.getAll();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (ServiceException e) {
			LOGGER.error(SERVICE_EXCEPTION, e);

			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping
	@ApiOperation(value = "UpdateOrganization API")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Organization updated Successfully", response = OrganizationDetails.class),
			@ApiResponse(code = 500, message = "Organization cannot be updated", response = Message.class) })
	public ResponseEntity<?> update(@RequestBody ManageOrganizationDTO organizationDetails) {

		try {
			organizationService.manageOrganization(organizationDetails);
			return new ResponseEntity<>("organization updated successfully", HttpStatus.OK);

		} catch (ServiceException e) {
			LOGGER.error(SERVICE_EXCEPTION, e);

			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	@ApiOperation(value = "delete logo and favicon")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Message.class),
			@ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<?> deleteQuestion(@RequestParam("organizationId") int organizationId) {

		Boolean result = false;
		try {
			result = organizationService.deleteLogo(organizationId);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (ServiceException e) {
			LOGGER.error(SERVICE_EXCEPTION, e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
