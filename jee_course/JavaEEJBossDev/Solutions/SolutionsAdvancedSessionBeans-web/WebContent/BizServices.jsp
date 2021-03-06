<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Management - Business Services</title>
    </head>
    <body style="font-family: arial">
        <h2>Business Services</h2>
        
        <form action="ClientServlet" method="post">
            <h3>Account Management Services</h3>
            <table>
                <tr valign="top">
                    <td>
                        <input type="text" name="AccountNumber"   value="Account number"/> <br/>
                        <input type="text" name="CustomerName"    value="Customer name"/>  <br/>
                        <input type="text" name="CustomerAddress" value="Customer address"/>  
                    </td>
                    <td>
                        <input type="submit" name="OpenAccount"  value="Open Account"/> &nbsp;&nbsp;
                        <input type="submit" name="CloseAccount" value="Close Account"/>
                    </td>
                </tr>
            </table>
        </form>
        
        <br/><br/>
        <form action="ClientServlet" method="post">
            <h3>Account Activity Services</h3>
            <table>
                <tr valign="top">
                    <td>
                        <input type="text" name="AccountNumber"   value="Account number"/> <br/>
                        <input type="text" name="Amount"          value="Amount"/>  <br/>
                    </td>
                    <td>
                        <input type="submit" name="Deposit"      value="Deposit"/> &nbsp;&nbsp;
                        <input type="submit" name="Withdraw"     value="Withdraw"/> &nbsp;&nbsp;
                        <input type="submit" name="QueryBalance" value="Query Balance"/> 
                    </td>
                </tr>
            </table>
        </form>
        
        <br/><br/>
        <form action="ClientServlet" method="post">
            <h3>Account Fund Redistribution Services</h3>
            <table>
                <tr valign="top">
                    <td>
                        <input type="text" name="FromAccountNumber" value="From account number"/> <br/>
                        <input type="text" name="ToAccountNumbers"  value="To (comma-separated account numbers)"/>  <br/>
                    </td>
                    <td>
                        <input type="submit" name="RedistributeFunds"  value="Redistribute Funds"/>
                    </td>
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
