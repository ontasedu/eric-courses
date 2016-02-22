-- ===================================================
-- MYSCHEMA - Main schema for course 
-- ===================================================

-- Drop tables.
DROP TABLE MYSCHEMA.SKILLS;
DROP TABLE MYSCHEMA.CONTRACTS;
DROP TABLE MYSCHEMA.EMPLOYEES;
DROP TABLE MYSCHEMA.EMPLOYEES2;
DROP TABLE MYSCHEMA.PERSONS;
DROP TABLE MYSCHEMA.DEPARTMENTS;

-- Drop stored procedures and functions.
DROP PROCEDURE MYSCHEMA.GetRegionInfo;
DROP PROCEDURE MYSCHEMA.UpdateSalaries;
DROP FUNCTION MYSCHEMA.GetRegionWageBill;
CALL SQLJ.REMOVE_JAR('MYSCHEMA.MySProcs', 0);

-- Drop schema.
DROP SCHEMA MYSCHEMA RESTRICT; 


-- ===================================================
-- MYCOLLSCHEMA - Schema for value-type collections.
-- ===================================================

-- Drop tables.
DROP TABLE MYCOLLSCHEMA.PHONENUMBERCOMPONENTS_FOR_SET;
DROP TABLE MYCOLLSCHEMA.PHONENUMBERS_FOR_LIST;
DROP TABLE MYCOLLSCHEMA.PHONENUMBERS_FOR_SET;
DROP TABLE MYCOLLSCHEMA.PERSONS;

-- Drop schema.
DROP SCHEMA MYCOLLSCHEMA RESTRICT; 


-- ===================================================
-- MYASSOC_xx_SCHEMA - Schema for entity collections.
-- ===================================================

-- Drop tables.
DROP TABLE MYASSOC_11_SCHEMA.CUSTOMERS;
DROP TABLE MYASSOC_11_SCHEMA.ADDRESSES;
DROP TABLE MYASSOC_11_SCHEMA.CARDS;

DROP TABLE MYASSOC_1M_SCHEMA.PHONES;
DROP TABLE MYASSOC_1M_SCHEMA.JUNKMAILS;
DROP TABLE MYASSOC_1M_SCHEMA.ORDERS;
DROP TABLE MYASSOC_1M_SCHEMA.CUSTOMERS;
	
DROP TABLE MYASSOC_MM_SCHEMA.PURCHASES;
DROP TABLE MYASSOC_MM_SCHEMA.CUSTOMERS;
DROP TABLE MYASSOC_MM_SCHEMA.PRODUCTS;

DROP TABLE MYASSOC_MMEXTRA_SCHEMA.PURCHASESEXTRA;
DROP TABLE MYASSOC_MMEXTRA_SCHEMA.CUSTOMERS;
DROP TABLE MYASSOC_MMEXTRA_SCHEMA.PRODUCTS;

-- Drop schemas.
DROP SCHEMA MYASSOC_11_SCHEMA RESTRICT;
DROP SCHEMA MYASSOC_1M_SCHEMA RESTRICT;
DROP SCHEMA MYASSOC_MM_SCHEMA RESTRICT;
DROP SCHEMA MYASSOC_MMEXTRA_SCHEMA RESTRICT;


-- ===================================================
-- MYINHSCHEMA - Schema for Inheritance/hierarchies  
-- ===================================================

-- Drop tables.
DROP TABLE MYINHSCHEMA.FOOTBALLFANS_MIXED;

DROP TABLE MYINHSCHEMA.PERSONS_SINGLETABLE;

DROP TABLE MYINHSCHEMA.EMPLOYEES_TABLE_PER_CONCRETE_CLASS;
DROP TABLE MYINHSCHEMA.CONTRACTORS_TABLE_PER_CONCRETE_CLASS;

DROP TABLE MYINHSCHEMA.EMPLOYEES_TABLE_PER_SUBCLASS;
DROP TABLE MYINHSCHEMA.CONTRACTORS_TABLE_PER_SUBCLASS;
DROP TABLE MYINHSCHEMA.PERSONS_TABLE_PER_SUBCLASS;

DROP TABLE MYINHSCHEMA.PERSONS_MIXED;

-- Drop schema.
DROP SCHEMA MYINHSCHEMA RESTRICT; 


-- ===================================================
-- MYOBJSCHEMA - Schema for Working with Objects.
-- ===================================================

-- Drop tables.
DROP TABLE MYOBJSCHEMA.SKILLS;
DROP TABLE MYOBJSCHEMA.EMPLOYEES;

-- Drop MYOBJSCHEMA schema.
DROP SCHEMA MYOBJSCHEMA RESTRICT;

