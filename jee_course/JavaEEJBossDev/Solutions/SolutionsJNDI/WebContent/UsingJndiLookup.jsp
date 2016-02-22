<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="javax.naming.*,javax.sql.*,solutions.jndi.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Using JNDI Lookup</title>
</head>
<body>

	<%
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/DerbyDS");

		DbHelper db = new DbHelper(ds, out);
		db.doDbOperations();
	%>

</body>
</html>