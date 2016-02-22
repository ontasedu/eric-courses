-- ===================================================
-- MYSCHEMA - Main schema 
-- ===================================================

-- Create MYSCHEMA schema.
CREATE SCHEMA MYSCHEMA;

-- Create EMPLOYEES table.
CREATE TABLE MYSCHEMA.EMPLOYEES (
	EmployeeID INT GENERATED ALWAYS as IDENTITY,
	Name VARCHAR(50) NOT NULL,
	Salary DOUBLE NOT NULL,
	Region VARCHAR(50) NOT NULL,
	PRIMARY KEY (EmployeeID)
);


-- Create EMPLOYEES2 table (has a BLOB column).
CREATE TABLE MYSCHEMA.EMPLOYEES2 (
	EmployeeID INT GENERATED ALWAYS as IDENTITY,
	Name VARCHAR(50) NOT NULL,
	Salary DOUBLE NOT NULL,
	Region VARCHAR(50) NOT NULL,
	Photo BLOB(16M),
	PRIMARY KEY (EmployeeID)
);


-- Create SKILLS table (has FK into EMPLOYEES).
CREATE TABLE MYSCHEMA.SKILLS (
	SkillID INT GENERATED ALWAYS as IDENTITY,
	EmployeeID INT CONSTRAINT EmployeeFK REFERENCES MYSCHEMA.EMPLOYEES,
	Description VARCHAR(50) NOT NULL,
	Level INT DEFAULT 5,
	PRIMARY KEY (SkillID)
);


-- Create CONTRACTS table (has FK into EMPLOYEES).
CREATE TABLE MYSCHEMA.CONTRACTS (
	EmployeeID INT CONSTRAINT EmployeeContractFK REFERENCES MYSCHEMA.EMPLOYEES,
	StartDate DATE NOT NULL,
	StartSalary DOUBLE NOT NULL,
	PRIMARY KEY (EmployeeID)
);


-- Create PERSONS table.
CREATE TABLE MYSCHEMA.PERSONS (
	PersonID INT GENERATED ALWAYS as IDENTITY,
	FirstName VARCHAR(50) NOT NULL,
	LastName  VARCHAR(50) NOT NULL,
	Address1  VARCHAR(50) NOT NULL,
	Address2  VARCHAR(50) NOT NULL,
	Address3  VARCHAR(50) NOT NULL,
	PRIMARY KEY (PersonID)
);


-- Create DEPARTMENTS table.
CREATE TABLE MYSCHEMA.DEPARTMENTS(
	DepartmentID INT GENERATED ALWAYS as IDENTITY,
	RegulatoryName VARCHAR(50),
	CommonName VARCHAR(50),
	PRIMARY KEY (DepartmentID)
);


-- Install SPROC JAR file (containing stored procedure code) into MYSCHEMA schema.
CALL SQLJ.INSTALL_JAR('C:\JavaEEJBossDev\MySProcs.jar', 'MYSCHEMA.MySProcs', 0);


-- Add JAR file to Derby database classpath.
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.classpath', 'MYSCHEMA.MySProcs');


-- Define UpdateSalaries stored procedure (demo IN parameters).
CREATE PROCEDURE MYSCHEMA.UpdateSalaries(rate DOUBLE, region VARCHAR(50)) 
	PARAMETER STYLE JAVA
	LANGUAGE JAVA
	MODIFIES SQL DATA
	EXTERNAL NAME 'mysprocs.MySProcs.updateSalaries';

	
-- Define GetRegionInfo stored procedure (demo OUT parameters).
CREATE PROCEDURE MYSCHEMA.GetRegionInfo(region VARCHAR(50), OUT avgSal DOUBLE, OUT minSal DOUBLE, OUT maxSal DOUBLE) 
	PARAMETER STYLE JAVA
	LANGUAGE JAVA
	READS SQL DATA
	EXTERNAL NAME 'mysprocs.MySProcs.getRegionInfo';

	
-- Define GetRegionWageBill stored procedure (demo return values).
CREATE FUNCTION MYSCHEMA.GetRegionWageBill(region VARCHAR(50)) RETURNS DOUBLE 
	PARAMETER STYLE JAVA
	LANGUAGE JAVA
	READS SQL DATA
	EXTERNAL NAME 'mysprocs.MySProcs.getRegionWageBill';


-- ===================================================
-- MYCOLLSCHEMA - Schema for value-type collections.
-- ===================================================

	
-- Create MYCOLLSCHEMA schema.
CREATE SCHEMA MYCOLLSCHEMA;


-- Create table for persons (this is the "one" side of the collection).
CREATE TABLE MYCOLLSCHEMA.PERSONS (
	PersonID INT GENERATED ALWAYS as IDENTITY,
	Name VARCHAR(50) NOT NULL,
	Address VARCHAR(50) NOT NULL,
	PRIMARY KEY (PersonID)
);


-- Create table for phone numbers for use in a SET (this is the "many" side of a set collection).
CREATE TABLE MYCOLLSCHEMA.PHONENUMBERS_FOR_SET (
	PersonID INT CONSTRAINT PersonPhoneNumbersForSetFK REFERENCES MYCOLLSCHEMA.PERSONS,
	Number VARCHAR(50) NOT NULL,
	PRIMARY KEY (PersonID, Number)
);


-- Create table for phone numbers for use in a LIST (this is the "many" side of a list collection).
CREATE TABLE MYCOLLSCHEMA.PHONENUMBERS_FOR_LIST (
	PersonID INT CONSTRAINT PersonPhoneNumbersForListFK REFERENCES MYCOLLSCHEMA.PERSONS,
	Position INT NOT NULL,
	Number VARCHAR(50) NOT NULL,
	PRIMARY KEY (PersonID, Position)
);


-- Create table for phone number components (e.g. for use in a set).
CREATE TABLE MYCOLLSCHEMA.PHONENUMBERCOMPONENTS_FOR_SET (
	PersonID INT CONSTRAINT PersonPhoneNumberComponentsForSetFK REFERENCES MYCOLLSCHEMA.PERSONS,
	AreaCode VARCHAR(10) NOT NULL,
	LocalNumber VARCHAR(10) NOT NULL,
	Description VARCHAR(10) NOT NULL,
	PRIMARY KEY (PersonID, AreaCode, LocalNumber, Description)
);


-- ===================================================
-- MYASSOC_xx_SCHEMA - Schemas for entity associations.
-- ===================================================


-- Create MYASSOC_11_SCHEMA schema, for 1-1 associations.
CREATE SCHEMA MYASSOC_11_SCHEMA;


-- Create table for addresses.
CREATE TABLE MYASSOC_11_SCHEMA.ADDRESSES(
	ID INT GENERATED ALWAYS as IDENTITY,
	Street VARCHAR(50) NOT NULL,
	City VARCHAR(50) NOT NULL,
	PRIMARY KEY (ID)
);


-- Create table for credit cards.
CREATE TABLE MYASSOC_11_SCHEMA.CARDS (
	ID INT GENERATED ALWAYS as IDENTITY,
	Number VARCHAR(50) NOT NULL,
	ExpDate DATE NOT NULL,
	PRIMARY KEY (ID)
);


-- Create table for customers.
CREATE TABLE MYASSOC_11_SCHEMA.CUSTOMERS (
	ID INT GENERATED ALWAYS as IDENTITY,
	Name VARCHAR(50) NOT NULL,
	AddressID INT CONSTRAINT CustomerAddressFK REFERENCES MYASSOC_11_SCHEMA.ADDRESSES,
	CardID    INT CONSTRAINT CustomerCardFK    REFERENCES MYASSOC_11_SCHEMA.CARDS,
	PRIMARY KEY (ID)
);


-- Create MYASSOC_1M_SCHEMA schema, for 1-many associations.
CREATE SCHEMA MYASSOC_1M_SCHEMA;


-- Create table for customers (this is the "one" side of the associations).
CREATE TABLE MYASSOC_1M_SCHEMA.CUSTOMERS (
	ID INT GENERATED ALWAYS as IDENTITY,
	Name VARCHAR(50) NOT NULL,
	PRIMARY KEY (ID)
);


-- Create table for phone numbers (this is the "many" side of a unidirectional association, from customer-to-phone).
CREATE TABLE MYASSOC_1M_SCHEMA.PHONES (
	ID INT GENERATED ALWAYS as IDENTITY,
	Number VARCHAR(20) NOT NULL,
	CustomerID INT,
	PRIMARY KEY (ID)
);


-- Create table for junkmail (this is the "many" side of a unidirectional association, from junkmail-to-customer).
CREATE TABLE MYASSOC_1M_SCHEMA.JUNKMAILS (
	ID INT GENERATED ALWAYS as IDENTITY,
	CustomerID INT CONSTRAINT CustomerJunkMailFK REFERENCES MYASSOC_1M_SCHEMA.CUSTOMERS,
	Subject VARCHAR(20) NOT NULL,
	PRIMARY KEY (ID)
);


-- Create table for orders (this is the "many" side of a bidirectional association, between customer-orders).
CREATE TABLE MYASSOC_1M_SCHEMA.ORDERS (
	ID INT GENERATED ALWAYS as IDENTITY,
	When DATE NOT NULL,
	Amount DOUBLE NOT NULL,
	CustomerID INT CONSTRAINT CustomerOrderFK REFERENCES MYASSOC_1M_SCHEMA.CUSTOMERS,
	PRIMARY KEY (ID)
);


-- Create MYASSOC_MM_SCHEMA schema, for many-many associations.
CREATE SCHEMA MYASSOC_MM_SCHEMA;


-- Create table for customers.
CREATE TABLE MYASSOC_MM_SCHEMA.CUSTOMERS (
	ID INT GENERATED ALWAYS as IDENTITY,
	Name VARCHAR(50) NOT NULL,
	PRIMARY KEY (ID)
);


-- Create table for products.
CREATE TABLE MYASSOC_MM_SCHEMA.PRODUCTS (
	ID INT GENERATED ALWAYS as IDENTITY,
	Descr VARCHAR(50) NOT NULL,
	Price DOUBLE NOT NULL,
	PRIMARY KEY (ID)
);


-- Create table for purchases.
CREATE TABLE MYASSOC_MM_SCHEMA.PURCHASES (
	CID  INT CONSTRAINT PurchasesCustomersFK REFERENCES MYASSOC_MM_SCHEMA.CUSTOMERS,
	PRID INT CONSTRAINT PurchasesProductsFK  REFERENCES MYASSOC_MM_SCHEMA.PRODUCTS,
	PRIMARY KEY (CID, PRID)
);


-- Create MYASSOC_MMEXTRA_SCHEMA schema, for many-many associations with extra info in the join column.
CREATE SCHEMA MYASSOC_MMEXTRA_SCHEMA;


-- Create table for customers.
CREATE TABLE MYASSOC_MMEXTRA_SCHEMA.CUSTOMERS (
	ID INT GENERATED ALWAYS as IDENTITY,
	Name VARCHAR(50) NOT NULL,
	PRIMARY KEY (ID)
);


-- Create table for products.
CREATE TABLE MYASSOC_MMEXTRA_SCHEMA.PRODUCTS (
	ID INT GENERATED ALWAYS as IDENTITY,
	Descr VARCHAR(50) NOT NULL,
	Price DOUBLE NOT NULL,
	PRIMARY KEY (ID)
);


-- Create table for purchases.
CREATE TABLE MYASSOC_MMEXTRA_SCHEMA.PURCHASESEXTRA (
	CID  INT CONSTRAINT PurchasesExtraCustomersFK REFERENCES MYASSOC_MMEXTRA_SCHEMA.CUSTOMERS,
	PRID INT CONSTRAINT PurchasesExtraProductsFK  REFERENCES MYASSOC_MMEXTRA_SCHEMA.PRODUCTS,
	Quantity INT NOT NULL,
	When DATE NOT NULL,
	PRIMARY KEY (CID, PRID)
);


-- ===================================================
-- MYINHSCHEMA - Schema for inheritance hierarchies  
-- ===================================================

-- Create MYINHSCHEMA schema.
CREATE SCHEMA MYINHSCHEMA;


-- Create table for "Single table" inheritance strategy.
CREATE TABLE MYINHSCHEMA.PERSONS_SINGLETABLE (
	PersonID INT GENERATED ALWAYS as IDENTITY,
	PersonType VARCHAR(10) NOT NULL,
	Name VARCHAR(50) NOT NULL,
	Address VARCHAR(50) NOT NULL,
	Emp_DateJoined DATE,
	Emp_Salary DOUBLE,
	Con_ContractLength INT,
	Con_DailyRate DOUBLE,
	PRIMARY KEY (PersonID)
);


-- Create tables for "Table per concrete class" inheritance strategy.
CREATE TABLE MYINHSCHEMA.EMPLOYEES_TABLE_PER_CONCRETE_CLASS (
	PersonID INT,
	Name VARCHAR(50) NOT NULL,
	Address VARCHAR(50) NOT NULL,
	DateJoined DATE NOT NULL,
	Salary DOUBLE NOT NULL,
	PRIMARY KEY (PersonID)
);


CREATE TABLE MYINHSCHEMA.CONTRACTORS_TABLE_PER_CONCRETE_CLASS (
	PersonID INT,
	Name VARCHAR(50) NOT NULL,
	Address VARCHAR(50) NOT NULL,
	ContractLength INT NOT NULL,
	DailyRate DOUBLE NOT NULL,
	PRIMARY KEY (PersonID)
);


-- Create tables for "Table per subclass" inheritance strategy.
CREATE TABLE MYINHSCHEMA.PERSONS_TABLE_PER_SUBCLASS (
	PersonID INT GENERATED ALWAYS as IDENTITY,
	Name VARCHAR(50) NOT NULL,
	Address VARCHAR(50) NOT NULL,
	PRIMARY KEY (PersonID)
);


CREATE TABLE MYINHSCHEMA.EMPLOYEES_TABLE_PER_SUBCLASS (
	EmployeeID INT CONSTRAINT EmployeesPersonsPerSubclassFK REFERENCES MYINHSCHEMA.PERSONS_TABLE_PER_SUBCLASS,
	DateJoined DATE NOT NULL,
	Salary DOUBLE NOT NULL,
	PRIMARY KEY (EmployeeID)
);


CREATE TABLE MYINHSCHEMA.CONTRACTORS_TABLE_PER_SUBCLASS (
	ContractorID INT CONSTRAINT ContractorsPersonsPerSubclassFK REFERENCES MYINHSCHEMA.PERSONS_TABLE_PER_SUBCLASS,
	ContractLength INT NOT NULL,
	DailyRate DOUBLE NOT NULL,
	PRIMARY KEY (ContractorID)
);


-- Create tables for "mixed inheritance strategy".
CREATE TABLE MYINHSCHEMA.PERSONS_MIXED (
	PersonID INT GENERATED ALWAYS as IDENTITY,
	PersonType VARCHAR(10) NOT NULL,
	Name VARCHAR(50) NOT NULL,
	Address VARCHAR(50) NOT NULL,
	Emp_DateJoined DATE,
	Emp_Salary DOUBLE,
	Con_ContractLength INT,
	Con_DailyRate DOUBLE,
	PRIMARY KEY (PersonID)
);


CREATE TABLE MYINHSCHEMA.FOOTBALLFANS_MIXED (
	FootballFanID INT NOT NULL CONSTRAINT FootballFansPersonsMixed REFERENCES MYINHSCHEMA.PERSONS_MIXED,
	Team VARCHAR(50) NOT NULL,
	GamesWatched INT NOT NULL,
	PRIMARY KEY (FootballFanID)
);


-- ===================================================
-- MYOBJSCHEMA - Schema for Working with Objects.
-- ===================================================

-- Create MYOBJSCHEMA schema.
CREATE SCHEMA MYOBJSCHEMA;


-- Create employees table.
CREATE TABLE MYOBJSCHEMA.EMPLOYEES (
	EmployeeID INT GENERATED ALWAYS as IDENTITY,
	Name VARCHAR(50) NOT NULL,
	Salary DOUBLE NOT NULL,
	Region VARCHAR(50) NOT NULL,
	PRIMARY KEY (EmployeeID)
);


-- Create skills table (has FK into employees).
CREATE TABLE MYOBJSCHEMA.SKILLS (
	SkillID INT GENERATED ALWAYS as IDENTITY,
	EmployeeID INT CONSTRAINT EmployeeObjFK REFERENCES MYOBJSCHEMA.EMPLOYEES,
	Description VARCHAR(50) NOT NULL,
	Level INT DEFAULT 5,
	PRIMARY KEY (SkillID)
);
