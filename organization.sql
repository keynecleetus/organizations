CREATE DATABASE revature_organization;
USE revature_organization;
CREATE TABLE employee_details
(
  id BIGINT AUTO_INCREMENT,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NOT NULL,
  email VARCHAR(50) NOT NULL,
  mobile_no LONG NOT NULL,
  salesforce_id VARCHAR(18) ,
  title VARCHAR(50) NOT NULL,
  timezone TIMESTAMP NOT NULL,
  country VARCHAR(30) NOT NULL,
  created_by VARCHAR(15) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  modified_by VARCHAR(15),
  modified_date TIMESTAMP ,
  status BOOLEAN DEFAULT 'active',
  CONSTRAINT pk_employee_user_id PRIMARY KEY(id),
  CONSTRAINT uk_employee_last_name UNIQUE(last_name),
  CONSTRAINT uk_employee_email UNIQUE(email),
  CONSTRAINT uk_employee_mobile_no UNIQUE(mobile_no)
  );
CREATE TABLE employee_role
( 
  id BIGINT  AUTO_INCREMENT,
  employee_id INT NOT NULL,
  organization_id INT NOT NULL,
  role_id INT NOT NULL,
  created_by VARCHAR(15) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  modified_by VARCHAR(15),
  modified_date TIMESTAMP ,
  CONSTRAINT fk_organization_id FOREIGN KEY(organization_id) REFERENCES organization_details(id),
  CONSTRAINT fk_employee_id FOREIGN KEY(employee_id) REFERENCES employee_details(id),
  CONSTRAINT fk_role_id FOREIGN KEY(role_id) REFERENCES role(id),
  CONSTRAINT pk_employee_role_id PRIMARY KEY(id)
  );
CREATE TABLE role
(
  id INT AUTO_INCREMENT,
  role VARCHAR(30) NOT NULL,
  created_by VARCHAR(15) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  status BOOLEAN DEFAULT 0,
  CONSTRAINT pk_role_id PRIMARY KEY(id),
  CONSTRAINT uk_role UNIQUE(role)
  );
CREATE TABLE organization_details
(
  id BIGINT AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  alias_name VARCHAR(40) NOT NULL,
  type VARCHAR(20) NOT NULL,
  salesforce_id VARCHAR(18)NOT NULL ,
  enable_user_verification BOOLEAN NOT NULL,
  sign_up_page BOOLEAN,
  theme_id INT,
  created_by VARCHAR(15) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  modified_by VARCHAR(15),
  modified_date TIMESTAMP ,
   status BOOLEAN DEFAULT 0,
  CONSTRAINT uk_alias_name_organization UNIQUE(alias_name),
  CONSTRAINT pk_organization_id PRIMARY KEY(id)
  );
CREATE TABLE organization_plans
 (
  id INT AUTO_INCREMENT,
  organization_id INT NOT NULL,
  name VARCHAR(50)NOT NULL,
  alias_name VARCHAR(50)NOT NULL,
  created_by VARCHAR(15) NOT NULL,
  created_date TIMESTAMP NOT NULL,
  modified_by VARCHAR(15),
  modified_date TIMESTAMP ,
  CONSTRAINT fk_plan_organization_id FOREIGN KEY(organization_id) REFERENCES organization_details(id),
  CONSTRAINT uk_alias_name_PLAN UNIQUE(alias_name)
  );
CREATE TABLE activation_code
  (
    id INT AUTO_INCREMENT,
    organization_id INT NOT NULL,
    activation_code INT NOT NULL,
    from_date DATE NOT NULL,
    to_date DATE NOT NULL,
	created_by VARCHAR(15) NOT NULL,
    created_date DATE NOT NULL,
    CONSTRAINT fk_organization_id FOREIGN KEY(organization_id) REFERENCES organization_details(id),
    CONSTRAINT uk_activation_code UNIQUE (activation_code),
    CONSTRAINT pk_activation_code_id PRIMARY KEY(id)
    );
CREATE TABLE domain
    (
     id INT AUTO_INCREMENT,
	 organization_id INT NOT NULL,
     domains VARCHAR(50) NOT NULL,
     created_by VARCHAR(15) NOT NULL,
     created_date DATE NOT NULL,
	 CONSTRAINT fk_organization_id FOREIGN KEY(organization_id) REFERENCES organization_details(id),
     CONSTRAINT pk_domain_id PRIMARY KEY(id),
	 CONSTRAINT uk_domain UNIQUE (domains)
     );
CREATE TABLE branding
   (
    id INT AUTO_INCREMENT,
    organization_id INT NOT NULL,
    logo VARCHAR(50) NOT NULL,
    favicon VARCHAR(50) NOT NULL,
	created_by VARCHAR(15) NOT NULL,
    created_date DATE NOT NULL,
	CONSTRAINT fk_organization_id FOREIGN KEY(organization_id) REFERENCES organization_details(id),
	CONSTRAINT pk_design_id PRIMARY KEY(id),
	CONSTRAINT uk_logo UNIQUE (logo),
	CONSTRAINT uk_favicon UNIQUE (favicon)
 );
CREATE TABLE themes
  (
   id INT AUTO_INCREMENT,
   themes VARCHAR(40) NOT NULL,
   created_by VARCHAR(15) NOT NULL,
   created_date DATE NOT NULL,
    status BOOLEAN DEFAULT 'active',
   CONSTRAINT pk_design_id PRIMARY KEY(id),
   CONSTRAINT uk_theme UNIQUE (themes)
 );
   
   
    
   
    
    
    