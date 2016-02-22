<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Management - Server Error</title>
    </head>
    <body style="font-family: arial">
        <h2>Server Error!</h2>
        
        Sorry, a server error occurred.

        <br/><br/>
        <a href="index.jsp">Home</a> &nbsp;&nbsp;
        <a href="LogoutServlet">Logout</a>

        <br/><br/>
        <% if (exception == null) {
               out.println("No exception details available.");
           } else {
               out.println("Detailed error message: " + exception.getMessage());
           }
        %>

    </body>
</html>
