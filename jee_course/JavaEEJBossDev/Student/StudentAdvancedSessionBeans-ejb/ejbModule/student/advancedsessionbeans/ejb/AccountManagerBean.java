package student.advancedsessionbeans.ejb;

import javax.ejb.Stateless;

import java.sql.SQLException;
import java.util.List;

@Stateless
public class AccountManagerBean implements AccountManagerBeanRemote {
    
    private AccountsDACBeanLocal accountsDACBean;
    private LogDACBeanLocal logDACBean;
    
    private double maxWithdrawal = 100;
    private double handlingFee = 2.50;

    @Override
    public void createTables() throws SQLException {
        logDACBean.createTable();
        accountsDACBean.createTable();
    }
    
    @Override
    public void dropTables() throws SQLException {
        logDACBean.dropTable();
        accountsDACBean.dropTable();
    }
    
    @Override
    public void openAccount(int accountNumber, String customerName, String customerAddress) throws SQLException, AccountException {
        logDACBean.insertActivity(accountNumber, "Opening account");
        accountsDACBean.insertAccount(accountNumber, customerName, customerAddress);
    }

    @Override
    public void closeAccount(int accountNumber) throws SQLException, AccountException {
        logDACBean.insertActivity(accountNumber, "Closing account");
        accountsDACBean.deleteAccount(accountNumber);
    }
    
    @Override
    public double depositInAccount(int accountNumber, double amount) throws SQLException, AccountException {
        logDACBean.insertActivity(accountNumber, "Depositing " + amount);
        accountsDACBean.deposit(accountNumber, amount);
        return accountsDACBean.getBalance(accountNumber);
    }
    
    @Override
    public double withdrawFromAccount(int accountNumber, double amount) throws SQLException, AccountException {

        if (amount > maxWithdrawal) {
            throw new AccountException("Cannout withdraw " + amount + " - max withdrawal is " + maxWithdrawal);

        } else {
            logDACBean.insertActivity(accountNumber, "Withdrawing " + amount);
            accountsDACBean.withdraw(accountNumber, amount);
        }
        
        double balance = accountsDACBean.getBalance(accountNumber);
        if (balance < 0) {
            System.out.printf("Account " + accountNumber + " is overdrawn (balance " + balance + "), charge handling fee " + handlingFee);
        }
        return balance;
    }
    
    @Override
    public void dumpActivityLog() throws SQLException {
        logDACBean.dumpActivities();
    }
    
    @Override
    public double queryBalance(int accountNumber) throws SQLException, AccountException {
        return accountsDACBean.getBalance(accountNumber);
    }

    @Override
    public void redistributeFundsFromAccount(int fromAccountNumber, List<Integer> toAccountNumbers) throws SQLException, AccountException {
        
        logDACBean.insertActivity(fromAccountNumber, "Redistributing all funds from account");

        double slice = accountsDACBean.getBalance(fromAccountNumber) / toAccountNumbers.size();
        
        for (int toAccNum : toAccountNumbers) {
            accountsDACBean.withdraw(fromAccountNumber, slice);
            accountsDACBean.deposit(toAccNum, slice);
            logDACBean.insertActivity(toAccNum, "Depositing " + slice);
        }
    }
}
