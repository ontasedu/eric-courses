package demos.advancedsessionbeans.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import demos.advancedsessionbeans.ejb.AccountManagerBeanRemote;

@WebServlet("/DemoTransactionsServlet")
public class DemoTransactionsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
      
	@EJB
    private AccountManagerBeanRemote accountManagerBean;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.print("In DemoTransactionsServlet, see console for messages");

        // Create the Accounts database table and insert account.
        try {
            accountManagerBean.createAccountsTable();
            accountManagerBean.createAccount(1, "Roberto", "Swansea");
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }


        // Demonstrate CMT transactions.
        demoCMTTransactions();
        
        // Demonstrate client-initiated transactions.
        // demoClientInitiatedTransactions();

        // Demonstrate BMT transactions.
        // demoBMTTransactions();

        // Drop the Accounts table.
        try {
            accountManagerBean.dropAccountsTable();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
	}


    // Demonstrate CMT transactions.
    private void demoCMTTransactions() {
        
        try {

        	// Uncomment one of the following sections of code, to illustrate how to cause a transaction to roll back.
        	
        	
            // Show what happens if you call a bean method that marks the transaction for rollback.
            /*
            try {
            
                accountManagerBean.simulateRollbackScenarios("MARK_ROLLBACK");
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            } finally {
                System.out.println("Account 1 balance is now " + accountManagerBean.getBalance(1) + ".");
            }
            */

        	
            // Show what happens if you call a bean method that throws a system exception.
        	/*
            try {
                accountManagerBean.simulateRollbackScenarios("SYSTEM_EXCEPTION");
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            } finally {
                System.out.println("Account 1 balance is now " + accountManagerBean.getBalance(1) + ".");
            }
            */


            // Show what happens if you call a bean method that throws an application exception.
        	/*
            try {
                accountManagerBean.simulateRollbackScenarios("APPLICATION_EXCEPTION");
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            } finally {
                System.out.println("Account 1 balance is now " + accountManagerBean.getBalance(1) + ".");
            }
            */


            // Show what happens if you call a bean method that throws an application exception marked for rollback.
        	/*
            try {
                accountManagerBean.simulateRollbackScenarios("ROLLBACK_APPLICATION_EXCEPTION");
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            } finally {
                System.out.println("Account 1 balance is now " + accountManagerBean.getBalance(1) + ".");
            }
            */

        } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
        }
    }
    
    
    // Demonstrate client-initiated transactions.
    private void demoClientInitiatedTransactions() {

        try {

        	// Obtain UserTransaction object, and either begin a new transaction or use existing transaction.
            UserTransaction trans = myBeginUserTransaction();

            
            // Uncomment one of the following sections of code, to illustrate how to cause a transaction to roll back.


            // Show what happens if the client marks the transaction for rollback.
            /*
            try {
                // Do some transactional tasks here, in the scope of the UserTransaction...

                trans.setRollbackOnly();

            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            } finally {
                myTerminateUserTransaction(trans);
            }
            */
            

            
            // Show what happens if you call a bean method that marks the transaction for rollback.
            /*
            trans = myBeginUserTransaction();
            try {
                // Do some transactional tasks here, in the scope of the UserTransaction...

                accountManagerBean.simulateUserTransactionRollbackScenarios("MARK_ROLLBACK");

            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            } finally {
                myTerminateUserTransaction(trans);
            }
            */

            
            // Show what happens if you call a bean method that throws a system exception.
            /*
            trans = myBeginUserTransaction();
            try {
                // Do some transactional tasks here, in the scope of the UserTransaction...

                accountManagerBean.simulateUserTransactionRollbackScenarios("SYSTEM_EXCEPTION");

            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            } finally {
                myTerminateUserTransaction(trans);
            }
            */


            // Show what happens if you call a bean method that throws an application exception.
            /*
            trans = myBeginUserTransaction();
            try {
                // Do some transactional tasks here, in the scope of the UserTransaction...

                accountManagerBean.simulateUserTransactionRollbackScenarios("APPLICATION_EXCEPTION");

            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            } finally {
                myTerminateUserTransaction(trans);
            }
            */

            
            // Show what happens if you call a bean method that throws an application exception marked for rollback.
            /*
            trans = myBeginUserTransaction();
            try {
                // Do some transactional tasks here, in the scope of the UserTransaction...

                accountManagerBean.simulateUserTransactionRollbackScenarios("ROLLBACK_APPLICATION_EXCEPTION");

            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            } finally {
                myTerminateUserTransaction(trans);
            }
            */

        } catch (Exception ex) {
            System.out.println("Exception occurred, so rolling back.");
        }
    }


    // Demonstrate BMT transactions.
    private void demoBMTTransactions() {

        // Create a list of sums to deposit into account.
        List<Double> amounts = new ArrayList<Double>();
        amounts.add(100.0);
        amounts.add(200.0);
        amounts.add(300.0);
        
        // Perform the multiple deposits in a BMT transaction.
        // bmtAccountManagerBean.multipleDeposits(1, amounts);
    }
    
    
    // Helper method, to begin a new UserTransaction or use an existing UserTransaction.
    private UserTransaction myBeginUserTransaction() throws NamingException, SystemException, NotSupportedException {

        Context context = new InitialContext();
        UserTransaction trans = (UserTransaction)context.lookup("java:comp/UserTransaction");

        if (trans.getStatus() != Status.STATUS_ACTIVE) {
            System.out.println("\nNo transaction active, so begin one.");
            trans.begin();
        } else {
            System.out.println("\nTransaction active, so use it.");
        }
        return trans;
    }
    
    
    // Helper method, to commit or rollback a UserTransaction.
    private void myTerminateUserTransaction(UserTransaction trans) throws SystemException, NotSupportedException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        
        myDisplayTransactionStatus(trans);
        if (trans.getStatus() == Status.STATUS_MARKED_ROLLBACK) {
            System.out.println("Transaction marked for rollback, so rolling back.");
            trans.rollback();
        } else {
            System.out.println("Committing the transaction.");
            trans.commit();
        }
    }
    
    
    private void myDisplayTransactionStatus(UserTransaction trans) throws SystemException {

        String statusString = "";
        switch (trans.getStatus()) {
            case Status.STATUS_ACTIVE:           statusString = "STATUS_ACTIVE";           break;
            case Status.STATUS_COMMITTED:        statusString = "STATUS_COMMITTED";        break;
            case Status.STATUS_COMMITTING:       statusString = "STATUS_COMMITTING";       break;
            case Status.STATUS_MARKED_ROLLBACK:  statusString = "STATUS_MARKED_ROLLBACK";  break;
            case Status.STATUS_NO_TRANSACTION:   statusString = "STATUS_NO_TRANSACTION";   break;
            case Status.STATUS_PREPARED:         statusString = "STATUS_PREPARED";         break;
            case Status.STATUS_PREPARING:        statusString = "STATUS_PREPARING";        break;
            case Status.STATUS_ROLLEDBACK:       statusString = "STATUS_ROLLEDBACK";       break;
            case Status.STATUS_ROLLING_BACK:     statusString = "STATUS_ROLLING_BACK";     break;
            case Status.STATUS_UNKNOWN:          statusString = "STATUS_UNKNOWN";          break;
        }
        System.out.println("Transaction status: " + trans.getStatus() + " (" + statusString + ").");
    }
}
