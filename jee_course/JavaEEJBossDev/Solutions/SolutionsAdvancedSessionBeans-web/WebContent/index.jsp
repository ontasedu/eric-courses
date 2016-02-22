<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Management</title>
    </head>
    <body style="font-family: arial">

        <h2>Account Management Home Page</h2>
        
        <% 
         if (request.getUserPrincipal() != null) {
             out.println("Logged in as: " + request.getUserPrincipal().getName());
         } else {
             out.println("Logged in as: [None]");
         }
        %>

        <br/><br/><br/>
        For Administrative services, click <a href="AdminServices.jsp">here</a>.
        <br/><br/>
        For Business services, click <a href="BizServices.jsp">here</a>.
        
        <br/><br/>
        <% 
         if (request.getUserPrincipal() != null) {
             out.println("<a href='LogoutServlet'>Logout</a>");
         }
        %>
                

    </body>
</html>
