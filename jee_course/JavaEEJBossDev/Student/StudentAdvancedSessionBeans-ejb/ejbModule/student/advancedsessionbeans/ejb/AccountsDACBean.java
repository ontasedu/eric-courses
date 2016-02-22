package student.advancedsessionbeans.ejb;

import javax.ejb.Stateless;
import javax.ejb.SessionContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.sql.DataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

@Stateless
public class AccountsDACBean implements AccountsDACBeanLocal {
    
    private DataSource myDatabase;

    private SessionContext sessionContext;

    @Override
    public void createTable() throws SQLException {

        Connection cn = null;
        try {
            String sql = "CREATE TABLE Accounts ( " +
                         "AccountId       INTEGER     NOT NULL, " +
                         "CustomerName    VARCHAR(50) NOT NULL, " +
                         "CustomerAddress VARCHAR(50) NOT NULL, " +
                         "Balance         DECIMAL     NOT NULL, " +
                         "CONSTRAINT PK_Accounts PRIMARY KEY (AccountId) " +
                         ")";

            cn = myDatabase.getConnection();
            Statement st = cn.createStatement();
            st.executeUpdate(sql);

        } finally {
            this.closeConnection(cn);
        }
    }

    @Override
    public void dropTable() throws SQLException {

        Connection cn = null;
        try {
            String sql = "DROP TABLE Accounts";

            cn = myDatabase.getConnection();
            Statement st = cn.createStatement();
            st.executeUpdate(sql);

        } finally {
            this.closeConnection(cn);
        }
    }

    @Override
    public void insertAccount(int accountNumber, String customerName, String customerAddress) throws SQLException, AccountException {

        Connection cn = null;
        try {
            String sql = "INSERT INTO Accounts VALUES (?, ?, ?, 0.0)";

            cn = myDatabase.getConnection();
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, accountNumber);
            st.setString(2, customerName);
            st.setString(3, customerAddress);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected != 1) {
                throw new AccountException("Cannot open account.");
            }

        } finally {
            this.closeConnection(cn);
        }
    }

    @Override
    public void deleteAccount(int accountNumber) throws SQLException, AccountException {

        Connection cn = null;
        try {
            String sql = "DELETE FROM Accounts WHERE AccountId=?";

            cn = myDatabase.getConnection();
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, accountNumber);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected != 1) {
                throw new AccountException("Cannot close account.");
            }

        } finally {
            this.closeConnection(cn);
        }
    }

    @Override
    public void deposit(int accountNumber, double amount) throws SQLException, AccountException {

        Connection cn = null;
        try {
            String sql = "UPDATE Accounts SET Balance = Balance + ? WHERE AccountId = ?";

            cn = myDatabase.getConnection();
            PreparedStatement st = cn.prepareStatement(sql);
            st.setDouble(1, amount);
            st.setInt(2, accountNumber);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected != 1) {
                throw new AccountException("Cannot deposit into account.");
            }

        } finally {
            this.closeConnection(cn);
        }
    }

    @Override
    public void withdraw(int accountNumber, double amount) throws SQLException, AccountException {

        Connection cn = null;
        try {
            String sql = "UPDATE Accounts SET Balance = Balance - ? WHERE AccountId = ?";

            cn = myDatabase.getConnection();
            PreparedStatement st = cn.prepareStatement(sql);
            st.setDouble(1, amount);
            st.setInt(2, accountNumber);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected != 1) {
                throw new AccountException("Cannot withdraw from account.");
            }

        } finally {
            this.closeConnection(cn);
        }
    }

    @Override
    public double getBalance(int accountNumber) throws SQLException, AccountException {

        Connection cn = null;
        double balance = 0.0;
        try {
            String sql = "SELECT Balance FROM Accounts WHERE AccountId = ?";

            cn = myDatabase.getConnection();
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, accountNumber);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                balance = rs.getDouble(1);
            } else {
                throw new AccountException("Cannot get balance for account.");
            }

        } finally {
            this.closeConnection(cn);
        }
        return balance;
    }

    @Override
    public double getBalanceDemoVersion(int accountNumber) throws SQLException, AccountException {

        Connection cn = null;
        double balance = 0.0;
        try {
            String sql = "SELECT Balance FROM Accounts WHERE AccountId = ?";

            cn = myDatabase.getConnection();
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, accountNumber);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                balance = rs.getDouble(1);
            } else {
                throw new AccountException("Cannot get balance for account.");
            }

            // Throw various types of exception, so we can try out transactional features.
            if (balance == 100) {
                throw new NullPointerException("Simulating a system exception (NullPointerException).");

            } else if (balance == 200) {
                throw new AccountException("Simulating a user-defined application exception (AccountException).");

            } else if (balance == 300) {
                throw new SQLException("Simulating a predefined application exception (SQLException).");
            }
            
        } finally {
            this.closeConnection(cn);
        }
        return balance;
    }

    private void closeConnection(Connection cn) {
        if (cn != null) {
            try { cn.close(); } catch (SQLException ex) {}
        }
    }
    
    @PostConstruct
    public void myPostConstruct() {
        System.out.println("AccountsDACBean postConstruct method " + new Date().toString());
    }
    
    @PreDestroy
    public void myPreDestroy() {
        System.out.println("AccountsDACBean preDestroy method " + new Date().toString());
    }
}
