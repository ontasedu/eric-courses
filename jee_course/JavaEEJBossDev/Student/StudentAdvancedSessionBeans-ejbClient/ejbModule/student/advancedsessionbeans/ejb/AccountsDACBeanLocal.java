package student.advancedsessionbeans.ejb;

import javax.ejb.Local;
import java.sql.SQLException;

@Local
public interface AccountsDACBeanLocal {

    public void createTable() throws SQLException;
    public void dropTable() throws SQLException;

    public void insertAccount(int accountNumber, String customerName, String customerAddress) throws SQLException, AccountException;
    public void deleteAccount(int accountNumber) throws SQLException, AccountException;
    
    public void deposit(int accountNumber, double amount) throws SQLException, AccountException;
    public void withdraw(int accountNumber, double amount) throws SQLException, AccountException;
    public double getBalance(int accountNumber) throws SQLException, AccountException;
    public double getBalanceDemoVersion(int accountNumber) throws SQLException, AccountException;
}
