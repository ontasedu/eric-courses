<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Management - Admin Services</title>
    </head>
    <body style="font-family: arial">
        <h2>Admin Services</h2>
        
        <form action="ClientServlet" method="post">
            <table>
                <tr>
                    <td>Create database tables</td>
                    <td><input type="submit" name="CreateTables" value="Create Tables"/></td>
                </tr>
                <tr>
                    <td>Drop database tables</td>
                    <td><input type="submit" name="DropTables" value="Drop Tables"/></td>
                </tr>
                <tr>
                    <td>Dump activity log</td>
                    <td><input type="submit" name="DumpLog" value="Dump Log"/></td>
                </tr>
            </table>
        </form>
        
        <p style="background-color:yellow">
        Status message: <b>${requestScope.statusMessage}</b>
        </p>

        <a href="index.jsp">Home</a> &nbsp;&nbsp;
        <a href="LogoutServlet">Logout</a>

    </body>
</html>
