package solutions.advancedsessionbeans.ejb;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AccountManagerBeanRemote {

    void createTables() throws SQLException;
    void dropTables() throws SQLException;
    
    public void openAccount(int accountNumber, String customerName, String customerAddress) throws SQLException, AccountException;
    public void closeAccount(int accountNumber) throws SQLException, AccountException;
    
    public double depositInAccount(int accountNumber, double amount) throws SQLException, AccountException;
    public double withdrawFromAccount(int accountNumber, double amount) throws SQLException, AccountException;
    public double queryBalance(int accountNumber) throws SQLException, AccountException;
    
    public void dumpActivityLog() throws SQLException;

    public void redistributeFundsFromAccount(int fromAccountNumber, List<Integer> toAccountNumbers) throws SQLException, AccountException;
}
