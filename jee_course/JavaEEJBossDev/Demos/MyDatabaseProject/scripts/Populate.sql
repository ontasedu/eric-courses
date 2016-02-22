-- ===========================================================================================================
-- Note: There's a bug in the Eclipse SQL parser. It can't parse ; (semicolon statement terminators) properly.
--       So, to execute statements in this file, do the following:
--         - Select a statement (not including the ;)
--         - Right-click
--         - Select Execute Selected Text from the popup menu 
-- ===========================================================================================================


-- ===================================================
-- MYSCHEMA - Main schema for course 
-- ===================================================

-- Populate EMPLOYEES table.
INSERT INTO MYSCHEMA.EMPLOYEES (Name, Salary, Region) VALUES 
	('Andy',   25000.00, 'South Wales'),
	('Claire', 37000.00, 'Kent'),
	('Mary',   42000.00, 'London'),
	('Mungo',  47000.00, 'Cumbria'),
	('Midge',  72000.00, 'Scotland'),
	('Hayley', 69000.00, 'Northern Ireland'),
	('Nicki',  22000.00, 'Kent'),
	('Sara',   11000.00, 'Kent'),
	('Fiona',  88000.00, 'Kent');

	
-- Populate EMPLOYEES2 table.
INSERT INTO MYSCHEMA.EMPLOYEES2 (Name, Salary, Region) VALUES 
	('Andy',   25000.00, 'South Wales'), 
	('Claire', 37000.00, 'Kent'),
	('Mary',   42000.00, 'London'),
	('Mungo',  47000.00, 'Cumbria'),
	('Midge',  72000.00, 'Scotland'),
	('Hayley', 69000.00, 'Northern Ireland'),
	('Nicki',  22000.00, 'Kent'),
	('Sara',   11000.00, 'Kent'),
	('Fiona',  88000.00, 'Kent');

	
-- Populate SKILLS table.
INSERT INTO MYSCHEMA.SKILLS (EmployeeID, Description, Level) VALUES 
	(1, 'Football', 5), 
	(1, 'Skiing',   3),
	(1, 'Running',  3),
	(2, 'Sales',    4),
	(2, 'Skiing',   4),
	(2, 'Football', 4),
	(3, 'Maths',    5),
	(3, 'Singing',  4),
	(3, 'Teaching', 5),
	(3, 'Running',  2);

	
-- Populate CONTRACTS table.
INSERT INTO MYSCHEMA.CONTRACTS (EmployeeID, StartDate, StartSalary) VALUES 
	(1,  '2008-1-1',  10000), 
	(2,  '2009-1-2',  20000),
	(3,  '2009-1-3',  30000),
	(4,  '2009-1-4',  40000),
	(5,  '2009-1-5',  50000),
	(6,  '2009-1-6',  60000),
	(7,  '2009-1-7',  70000),
	(8,  '2009-1-8',  80000),
	(9,  '2009-1-9',  90000);
	
	
-- Populate PERSONS table.
INSERT INTO MYSCHEMA.PERSONS (FirstName, LastName, Address1, Address2, Address3) VALUES
	('John', 'Smith', '1 Main St', 'Weston', 'Bath'),
	('Jane', 'Evans', '2 High St', 'Newton', 'Neath'),
	('Bill', 'Jones', '3 Oaks St', 'Denton', 'Leeds');

	
-- Populate DEPARTMENTS table.
INSERT INTO MYSCHEMA.DEPARTMENTS(RegulatoryName, CommonName) VALUES
	('MKT', 'Markets'),
	('ACC',  NULL),
	('HR',   NULL),
	(NULL,  'R&D'),
	(NULL,  'Admin');

	
-- ===================================================
-- MYCOLLSCHEMA - Schema for Collections 
-- ===================================================

-- Populate PERSONS table.
INSERT INTO MYCOLLSCHEMA.PERSONS (Name, Address) VALUES
	('Alex', 'Aberdeen'),
	('Bill', 'Bradford'),
	('Carl', 'Cardiff'),
	('Dawn', 'Dulwich');

	
-- Populate PHONENUMBERS_FOR_SET table.
INSERT INTO MYCOLLSCHEMA.PHONENUMBERS_FOR_SET (PersonID, Number) VALUES
	(1, '1-11111'),
	(1, '1-22222'),
	(2, '2-11111'),
	(2, '2-22222'),
	(3, '3-11111'),
	(3, '3-22222'),
	(4, '4-11111'),
	(4, '4-22222');

	
-- Populate PHONENUMBERS_FOR_LIST table.
INSERT INTO MYCOLLSCHEMA.PHONENUMBERS_FOR_LIST (PersonID, Position, Number) VALUES
	(1, 0, '1-11111'),
	(1, 1, '1-22222'),
	(2, 0, '2-11111'),
	(2, 1, '2-22222'),
	(3, 1, '3-11111'),
	(3, 0, '3-22222'),
	(4, 1, '4-11111'),
	(4, 0, '4-22222');

	
-- Populate PHONENUMBERCOMPONENTS_FOR_SET table.
INSERT INTO MYCOLLSCHEMA.PHONENUMBERCOMPONENTS_FOR_SET (PersonID, AreaCode, LocalNumber, Description) VALUES
	(1, '111', '1-11111', 'P1, home'),
	(1, '222', '1-22222', 'P1, work'),
	(2, '111', '2-11111', 'P2, home'),
	(2, '222', '2-22222', 'P2, work'),
	(3, '111', '3-11111', 'P3, home'),
	(3, '222', '3-22222', 'P3, work'),
	(4, '111', '4-11111', 'P4, home'),
	(4, '222', '4-22222', 'P4, work');
	

-- ===================================================
-- MYINHSCHEMA - Schema for Inheritance/hierarchies  
-- ===================================================

-- Populate PERSONS_SINGLETABLE table.
INSERT INTO MYINHSCHEMA.PERSONS_SINGLETABLE (PersonType, Name, Address, Emp_DateJoined, Emp_Salary, Con_ContractLength, Con_DailyRate) VALUES
	('EMP', 'Alex', 'Aberdeen', '2001-01-01', 10000, NULL, NULL),
	('EMP', 'Bill', 'Bradford', '2002-02-02', 20000, NULL, NULL),
	('CON', 'Carl', 'Cardiff',   NULL,        NULL,  30,   300),
	('CON', 'Dawn', 'Dulwich',   NULL,        NULL,  40,   400);

	
-- Populate EMPLOYEES_TABLE_PER_CONCRETE_CLASS table.
INSERT INTO MYINHSCHEMA.EMPLOYEES_TABLE_PER_CONCRETE_CLASS (PersonId, Name, Address, DateJoined, Salary) VALUES
	(1, 'Alex', 'Aberdeen', '2001-01-01', 10000),
	(2, 'Bill', 'Bradford', '2002-02-02', 20000);
	

-- Populate CONTRACTORS_TABLE_PER_CONCRETE_CLASS table.
INSERT INTO MYINHSCHEMA.CONTRACTORS_TABLE_PER_CONCRETE_CLASS (PersonId, Name, Address, ContractLength, DailyRate) VALUES
	(3, 'Carl', 'Cardiff', 30, 300),
	(4, 'Dawn', 'Dulwich', 40, 400);

	
-- Populate PERSONS_TABLE_PER_SUBCLASS table.
INSERT INTO MYINHSCHEMA.PERSONS_TABLE_PER_SUBCLASS (Name, Address) VALUES
	('Alex', 'Aberdeen'),
	('Bill', 'Bradford'),
	('Carl', 'Cardiff'),
	('Dawn', 'Dulwich');

	
-- Populate EMPLOYEES_TABLE_PER_SUBCLASS table.
INSERT INTO MYINHSCHEMA.EMPLOYEES_TABLE_PER_SUBCLASS (EmployeeID, DateJoined, Salary) VALUES
	(1, '2001-01-01', 10000),
	(2, '2002-02-02', 20000);

	
-- Populate MYINHSCHEMA.CONTRACTORS_TABLE_PER_SUBCLASS table.
INSERT INTO MYINHSCHEMA.CONTRACTORS_TABLE_PER_SUBCLASS (ContractorID, ContractLength, DailyRate) VALUES
	(3, 30, 300),
	(4, 40, 400);

	
-- Populate MYINHSCHEMA.PERSONS_MIXED table.
INSERT INTO MYINHSCHEMA.PERSONS_MIXED (PersonType, Name, Address, Emp_DateJoined, Emp_Salary, Con_ContractLength, Con_DailyRate) VALUES
	('EMP', 'Alex', 'Aberdeen', '2001-01-01', 10000, NULL, NULL),
	('EMP', 'Bill', 'Bradford', '2002-02-02', 20000, NULL, NULL),
	('CON', 'Carl', 'Cardiff',   NULL,        NULL,  30,   300),
	('CON', 'Dawn', 'Dulwich',   NULL,        NULL,  40,   400),
	('FAN', 'Andy', 'Swansea',   NULL,        NULL,  NULL, NULL),
	('FAN', 'Kris', 'Oslo',      NULL,        NULL,  NULL, NULL);
	
	
-- Populate FOOTBALLFANS_MIXED table.
INSERT INTO MYINHSCHEMA.FOOTBALLFANS_MIXED (FootballFanId, Team, GamesWatched) VALUES
	(5, 'Swansea City', 500),
	(6, 'Liverpool',    100);
	
	
-- ===================================================
-- MYOBJSCHEMA - Schema for Working with Objects
-- ===================================================

-- Populate EMPLOYEES table.
INSERT INTO MYOBJSCHEMA.EMPLOYEES (Name, Salary, Region) VALUES
	('Andy',   25000.00, 'South Wales'),
	('Nigel',  52000.00, 'Home Counties'),
	('Claire', 37000.00, 'Kent'),
	('Mary',   42000.00, 'London'),
	('Mungo',  47000.00, 'Cumbria'),
	('Midge',  72000.00, 'Scotland'),
	('Hayley', 69000.00, 'Northern Ireland'),
	('Nicki',  22000.00, 'Kent'),
	('Sara',   11000.00, 'Kent'),
	('Fiona',  88000.00, 'Kent');

	
-- Populate SKILLS table.
INSERT INTO MYOBJSCHEMA.SKILLS (EmployeeID, Description, Level) VALUES 
	(1, 'Football', 5), 
	(1, 'Skiing',   3),
	(1, 'Running',  3),
	(2, 'Sales',    4),
	(2, 'Skiing',   4),
	(2, 'Football', 4),
	(3, 'Maths',    5),
	(3, 'Singing',  4),
	(3, 'Teaching', 5),
	(3, 'Running',  2);

