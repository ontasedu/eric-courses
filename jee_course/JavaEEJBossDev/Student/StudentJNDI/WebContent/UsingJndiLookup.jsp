<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Using JNDI Lookup</title>
</head>
<body>

	<%
		// Create a JNDI InitialContext object, and use it to lookup the DataSource for the Derby database.

		
		// Create a DbHelper object, and pass 2 parameters into the constructor:
		//   - the DataSource object (you got via JNDI lookup above)
		//   - a Writer object that can write to the HTML response buffer (out)

		
		// Invoke doDbOperations() on the DbHelper object.

		
	%>

</body>
</html>