package solutions.advancedsessionbeans.ejb;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

@Stateless
@LocalBean
@EJB(name="ejb/local/statefulLogDACBean", beanInterface=solutions.advancedsessionbeans.ejb.StatefulLogDACBeanLocal.class)
@SecurityDomain(value = "other") 
@DeclareRoles({"Administrator", "Manager", "Clerk", "AccountHolder"})
public class AccountManagerBean implements AccountManagerBeanRemote {

    @EJB
    private AccountsDACBeanLocal accountsDACBean;

    @EJB
    private LogDACBeanLocal logDACBean;
    
    @Resource(name = "maxWithdrawalEntry")
    private double maxWithdrawal = 100;

    private double handlingFee = 2.50;

    @Resource
    private SessionContext sessionContext;

    
    @Override
    @RolesAllowed("Administrator")
    public void createTables() throws SQLException {
        try {
            logDACBean.createTable();
            accountsDACBean.createTable();
        } catch (SQLException ex) {
            throw new EJBException(ex);
        }
    }


    @Override
    @RolesAllowed("Administrator")
    public void dropTables() throws SQLException {
        try {
            logDACBean.dropTable();
            accountsDACBean.dropTable();
        } catch (SQLException ex) {
            throw new EJBException(ex);
        }
    }
    

    @Override
    @RolesAllowed("Manager")
    public void openAccount(int accountNumber, String customerName, String customerAddress) throws SQLException, AccountException {
        try {
            logDACBean.insertActivity(accountNumber, "Opening account");
            accountsDACBean.insertAccount(accountNumber, customerName, customerAddress);
        } catch (SQLException ex) {
            throw new EJBException(ex);
        }
    }


    @Override
    @RolesAllowed("Manager")
    public void closeAccount(int accountNumber) throws SQLException, AccountException {
        try {
            logDACBean.insertActivity(accountNumber, "Closing account");
            accountsDACBean.deleteAccount(accountNumber);
        } catch (SQLException ex) {
            throw new EJBException(ex);
        }
    }
    

    @Override
    @RolesAllowed({"AccountHolder", "Clerk"})
    public double depositInAccount(int accountNumber, double amount) throws SQLException, AccountException {
        try {
            logDACBean.insertActivity(accountNumber, "Depositing " + amount);
            accountsDACBean.deposit(accountNumber, amount);
            return accountsDACBean.getBalanceDemoVersion(accountNumber);
        } catch (SQLException ex) {
            throw new EJBException(ex);
        }
    }
    

    @Override
    @RolesAllowed({"AccountHolder", "Clerk"})
    public double withdrawFromAccount(int accountNumber, double amount) throws SQLException, AccountException {

        try {
        	if (amount > maxWithdrawal && !sessionContext.isCallerInRole("Clerk")) {
        		throw new AccountException("Cannout withdraw " + amount + " - max withdrawal is " + maxWithdrawal);

            } else {
                logDACBean.insertActivity(accountNumber, "Withdrawing " + amount);
                accountsDACBean.withdraw(accountNumber, amount);
            }

            double balance = accountsDACBean.getBalanceDemoVersion(accountNumber);
            if (balance < 0) {
                System.out.printf("Account " + accountNumber + " is overdrawn (balance " + balance + "), charge handling fee " + handlingFee);
            }
            return balance;

        } catch (SQLException ex) {
            throw new EJBException(ex);
        }
    }
    

    @Override
    @RolesAllowed("Administrator")
    public void dumpActivityLog() throws SQLException {
        try {
            logDACBean.dumpActivities();
        } catch (SQLException ex) {
            throw new EJBException(ex);
        }
    }
    
    
    @Override
    @RolesAllowed({"AccountHolder", "Clerk"})
    public double queryBalance(int accountNumber) throws SQLException, AccountException {
        try {
            return accountsDACBean.getBalance(accountNumber);
        } catch (SQLException ex) {
            throw new EJBException(ex);
        }
    }

    
    @Override
    @RolesAllowed("Clerk")
    public void redistributeFundsFromAccount(int fromAccountNumber, List<Integer> toAccountNumbers) throws SQLException, AccountException {
        
        try {
            StatefulLogDACBeanLocal statefulLogDACBean = (StatefulLogDACBeanLocal) sessionContext.lookup("ejb/local/statefulLogDACBean");
        
            statefulLogDACBean.insertActivity(fromAccountNumber, "Redistributing all funds from account");

            double slice = accountsDACBean.getBalance(fromAccountNumber) / toAccountNumbers.size();

            for (int toAccNum : toAccountNumbers) {
                accountsDACBean.withdraw(fromAccountNumber, slice);
                accountsDACBean.deposit(toAccNum, slice);
                statefulLogDACBean.insertActivity(toAccNum, "Depositing " + slice);
            }

        } catch (SQLException ex) {
            throw new EJBException(ex);
        }
    }
}
