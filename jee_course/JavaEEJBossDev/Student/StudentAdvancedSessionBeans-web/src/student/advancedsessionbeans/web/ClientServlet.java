package student.advancedsessionbeans.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.advancedsessionbeans.ejb.AccountException;
import student.advancedsessionbeans.ejb.AccountManagerBeanRemote;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private HttpServletRequest theRequest;
	
    private AccountManagerBeanRemote accountManagerBean;

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		theRequest = request;
		
		try {
	        if (request.getParameter("CreateTables") != null) {
	            doCreateTables();
	        } else if (request.getParameter("DropTables") != null) {
	            doDropTables();
	        } else if (request.getParameter("OpenAccount") != null) {
	            doOpenAccount();
	        } else if (request.getParameter("CloseAccount") != null) {
	            doCloseAccount();
	        } else if (request.getParameter("Deposit") != null) {
	            doDeposit();
	        } else if (request.getParameter("Withdraw") != null) {
	            doWithdraw();
	        } else if (request.getParameter("RedistributeFunds") != null) {
	            doRedistributeFunds();
	        } else if (request.getParameter("QueryBalance") != null) {
	            doQueryBalance();
	        } else if (request.getParameter("DumpLog") != null) {
	            doDumpActivityLog();
	        }
	    } 
		catch (SQLException ex) {
	        System.out.println("SQLException: " + ex.getMessage());
	    } 
		catch (AccountException ex) {
	        System.out.println("AccountException: " + ex.getMessage());
	    } 
		catch (Exception ex) {
	        System.out.println("Exception: " + ex.getMessage());
	    }
		
		String homeURL = response.encodeURL("/home.jsp");
        RequestDispatcher rd = request.getRequestDispatcher(homeURL);
        rd.forward(request, response);	
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	
    private void doCreateTables() throws SQLException {
        accountManagerBean.createTables();
        System.out.println("Created database tables.");
    }

    
    private void doDropTables() throws SQLException {
        accountManagerBean.dropTables();
        System.out.println("Dropped database tables.");
    }
    

    private void doOpenAccount() throws SQLException, AccountException {
        int accountNumber = Integer.parseInt(theRequest.getParameter("AccountNumber"));
        String customerName = theRequest.getParameter("CustomerName");
        String customerAddress = theRequest.getParameter("CustomerAddress");

        accountManagerBean.openAccount(accountNumber, customerName, customerAddress);
        System.out.printf("Opened account %d\n", accountNumber);
    }

    
    private void doCloseAccount() throws SQLException, AccountException {
        int accountNumber = Integer.parseInt(theRequest.getParameter("AccountNumber"));

        accountManagerBean.closeAccount(accountNumber);
        System.out.printf("Closed account %d\n", accountNumber);
    }

    
    private void doDeposit()  throws SQLException, AccountException {
        int accountNumber = Integer.parseInt(theRequest.getParameter("AccountNumber"));
        double amount = Double.parseDouble(theRequest.getParameter("Amount"));

        double newBalance = accountManagerBean.depositInAccount(accountNumber, amount);
        System.out.printf("Deposited %.2f, new balance is %.2f\n", amount, newBalance);
    }

    
    private void doWithdraw() throws SQLException, AccountException {
        int accountNumber = Integer.parseInt(theRequest.getParameter("AccountNumber"));
        double amount = Double.parseDouble(theRequest.getParameter("Amount"));

        double newBalance = accountManagerBean.withdrawFromAccount(accountNumber, amount);
        System.out.printf("Withdrawn %.2f, new balance is %.2f\n", amount, newBalance);
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
        System.out.printf("Redistributed all funds from account %d\n", fromAccountNumber);
    }

    
    private void doQueryBalance()  throws SQLException, AccountException {
        int accountNumber = Integer.parseInt(theRequest.getParameter("AccountNumber"));
        double balance = accountManagerBean.queryBalance(accountNumber);
        System.out.printf("Balance is %.2f\n", balance);
    }
    
    
    private void doDumpActivityLog()  throws SQLException {
        accountManagerBean.dumpActivityLog();
        System.out.println("Activity log dumped to server console window.");
    }
}
