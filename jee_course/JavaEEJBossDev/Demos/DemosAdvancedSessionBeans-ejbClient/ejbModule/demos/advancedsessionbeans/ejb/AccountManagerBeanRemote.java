package demos.advancedsessionbeans.ejb;

import java.sql.SQLException;
import javax.ejb.Remote;

@Remote
public interface AccountManagerBeanRemote {

    // Methods to demonstrate injection and dependencies. 
    public void displayBindings(String name);
    public void useEnvironmentEntries();
    public void useBeans();
    public void useExternalResources();

    // CMT business methods.
    public void createAccountsTable() throws AccountException, SQLException;
    public void dropAccountsTable() throws AccountException, SQLException;
    public void createAccount(int accountNumber, String customerName, String customerAddress) throws AccountException, SQLException;
    public void deposit(int accountNumber, double amount) throws AccountException, SQLException;
    public void withdraw(int accountNumber, double amount) throws AccountException, SQLException;
    public double getBalance(int accountNumber) throws AccountException, SQLException;

    // CMT business method that simulates rollback scenarios.
    public void simulateRollbackScenarios(String testMode) throws AccountException, SQLException;
    
    // CMT business method that simulates rollback scenarios when invoked within a user transaction.
    public void simulateUserTransactionRollbackScenarios(String testMode) throws AccountException, SQLException;
}
