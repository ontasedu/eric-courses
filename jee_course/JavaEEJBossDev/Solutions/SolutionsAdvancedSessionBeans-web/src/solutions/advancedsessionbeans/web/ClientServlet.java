package solutions.advancedsessionbeans.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import solutions.advancedsessionbeans.ejb.AccountException;
import solutions.advancedsessionbeans.ejb.AccountManagerBeanRemote;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private HttpServletRequest theRequest;
	
    @EJB
    private AccountManagerBeanRemote accountManagerBean;

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		theRequest = request;
		
		String pageToDisplay = "index.jsp";
		
		try {
	        if (request.getParameter("CreateTables") != null) {
	            doCreateTables();
	            pageToDisplay = "AdminServices.jsp";
	        } 
	        else if (request.getParameter("DropTables") != null) {
	            doDropTables();
	            pageToDisplay = "AdminServices.jsp";
	        } 
	        else if (request.getParameter("DumpLog") != null) {
	            doDumpActivityLog();
	            pageToDisplay = "AdminServices.jsp";
	        }
	        else if (request.getParameter("OpenAccount") != null) {
	            doOpenAccount();
	            pageToDisplay = "BizServices.jsp";
	        } 
	        else if (request.getParameter("CloseAccount") != null) {
	            doCloseAccount();
	            pageToDisplay = "BizServices.jsp";
	        } 
	        else if (request.getParameter("Deposit") != null) {
	            doDeposit();
	            pageToDisplay = "BizServices.jsp";
	        } 
	        else if (request.getParameter("Withdraw") != null) {
	            doWithdraw();
	            pageToDisplay = "BizServices.jsp";
	        } 
	        else if (request.getParameter("RedistributeFunds") != null) {
	            doRedistributeFunds();
	            pageToDisplay = "BizServices.jsp";
	        } 
	        else if (request.getParameter("QueryBalance") != null) {
	            doQueryBalance();
	            pageToDisplay = "BizServices.jsp";
	        }

			String homeURL = response.encodeURL(pageToDisplay);
	        RequestDispatcher rd = request.getRequestDispatcher(homeURL);
	        rd.forward(request, response);	
		} 
		catch (Exception ex) {
			throw new ServletException(ex);
		}		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	
    private void doCreateTables() throws SQLException {
        accountManagerBean.createTables();
        theRequest.setAttribute("statusMessage", "Created database tables.");
    }

    
    private void doDropTables() throws SQLException {
        accountManagerBean.dropTables();
        theRequest.setAttribute("statusMessage", "Dropped database tables.");
    }
    

    private void doOpenAccount() throws SQLException, AccountException {
        int accountNumber = Integer.parseInt(theRequest.getParameter("AccountNumber"));
        String customerName = theRequest.getParameter("CustomerName");
        String customerAddress = theRequest.getParameter("CustomerAddress");

        accountManagerBean.openAccount(accountNumber, customerName, customerAddress);
        theRequest.setAttribute("statusMessage", String.format("Opened account %d\n", accountNumber));
    }

    
    private void doCloseAccount() throws SQLException, AccountException {
        int accountNumber = Integer.parseInt(theRequest.getParameter("AccountNumber"));

        accountManagerBean.closeAccount(accountNumber);
        theRequest.setAttribute("statusMessage", String.format("Closed account %d\n", accountNumber));
    }

    
    private void doDeposit()  throws SQLException, AccountException {
        int accountNumber = Integer.parseInt(theRequest.getParameter("AccountNumber"));
        double amount = Double.parseDouble(theRequest.getParameter("Amount"));

        double newBalance = accountManagerBean.depositInAccount(accountNumber, amount);
        theRequest.setAttribute("statusMessage", String.format("Deposited %.2f, new balance is %.2f\n", amount, newBalance));
    }

    
    private void doWithdraw() throws SQLException, AccountException {
        int accountNumber = Integer.parseInt(theRequest.getParameter("AccountNumber"));
        double amount = Double.parseDouble(theRequest.getParameter("Amount"));

        double newBalance = accountManagerBean.withdrawFromAccount(accountNumber, amount);
        theRequest.setAttribute("statusMessage", String.format("Withdrawn %.2f, new balance is %.2f\n", amount, newBalance));
    }

    
    private void doRedistributeFunds() throws SQLException, AccountException {

        int fromAccountNumber = Integer.parseInt(theRequest.getParameter("FromAccountNumber"));
        String toAccountNumbersCollated = theRequest.getParameter("ToAccountNumbers");
        String[] toAccountNumbersSplit = toAccountNumbersCollated.split(",");
        
        ArrayList<Integer> toAccountNumbers = new ArrayList<Integer>();
        for (int i = 0; i < toAccountNumbersSplit.length; i++) {
                toAccountNumbers.add(Integer.parseInt(toAccountNumbersSplit[i]));
        }        
       
        accountManagerBean.redistributeFundsFromAccount(fromAccountNumber, toAccountNumbers);
        theRequest.setAttribute("statusMessage", String.format("Redistributed all funds from account %d\n", fromAccountNumber));
    }

    
    private void doQueryBalance()  throws SQLException, AccountException {
        int accountNumber = Integer.parseInt(theRequest.getParameter("AccountNumber"));
        double balance = accountManagerBean.queryBalance(accountNumber);
        theRequest.setAttribute("statusMessage", String.format("Balance is %.2f\n", balance));
    }
    
    
    private void doDumpActivityLog()  throws SQLException {
        accountManagerBean.dumpActivityLog();
        theRequest.setAttribute("statusMessage", "Activity log dumped to server console window.");
    }
}
