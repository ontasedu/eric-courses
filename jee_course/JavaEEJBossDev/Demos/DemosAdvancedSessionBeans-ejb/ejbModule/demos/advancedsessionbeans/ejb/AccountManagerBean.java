package demos.advancedsessionbeans.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Stateless
@LocalBean

//Add some beans into local JNDI namespace, so you can do local JNDI lookup.
@EJBs({
	@EJB(name="ejb/local/loggerBean", beanInterface=demos.advancedsessionbeans.ejb.LoggerBeanLocal.class)
	// Plus other @EJB annotations as required
})

// Add some external resources into local JNDI namespace, so you can do local JNDI lookup.
@Resources({
    @Resource(name="jdbc/local/DerbyDS",
              type=javax.sql.DataSource.class,
              mappedName="java:/DerbyDS")
    // Plus other @Resource annotations as required.
})

public class AccountManagerBean implements AccountManagerBeanRemote {

    // ------------------------------
    // Bean injection(s).
    // ------------------------------
    @EJB
    private LoggerBeanLocal loggerBean;     

    
    // ------------------------------
    // Environment entry injections.
    // ------------------------------
    @Resource(name = "maxWithdrawalEntry")
    private double maxWithdrawal = 100;

    @Resource(name = "maxOverdraftEntry")
    private double maxOverdraft = 1000;

    private double handlingFee = 2.50;

    
    // ------------------------------
    // SessionContext injection.
    // ------------------------------
    @Resource
    private SessionContext sessionContext;
    
    
    // ------------------------------
    // External resource injection.
    // ------------------------------
    @Resource(mappedName="java:/DerbyDS")
    private DataSource myDatabase;

    
    // Method to access other EJB beans.
    @Override
    public void useBeans() {

        System.out.println("===== In useBeans() method ================================================================");

        // Use injected bean(s).
        loggerBean.log("Injection used to obtain bean in same JAR.");
        
        try {
            // Lookup bean(s) in local JNDI namespace, by using conventional JNDI lookup.
            InitialContext context = new InitialContext();
            LoggerBeanLocal ll = (LoggerBeanLocal)context.lookup("java:comp/env/ejb/local/loggerBean");
            ll.log("InitialContext lookup used to obtain bean in same JAR.");

        } catch (NamingException ex) {
            System.out.println("NamingException: " + ex.getMessage());
        }
        
        // Lookup bean(s) in local JNDI namespace, by using EJBContext/SessionContext.
        LoggerBeanLocal ll = (LoggerBeanLocal) sessionContext.lookup("ejb/local/loggerBean");
        ll.log("EJBContext lookup used to obtain bean.");
    }
    
    
    // Method to access environment entries (as defined in ejb-jar.xml).
    @Override
    public void useEnvironmentEntries() {

        System.out.println("===== In useEnvironmentEntries() method ================================================================");

        // Use injected environment entries.
        System.out.println("Injected maxWithdrawal: " + maxWithdrawal);
        System.out.println("Injected maxOverdraft:  " + maxOverdraft);
        System.out.println("Injected handlingFee:   " + handlingFee);

        try {
            // Lookup environment entries in local JNDI namespace, by using conventional JNDI lookup.
            InitialContext context = new InitialContext();
            double mw = (Double) context.lookup("java:comp/env/maxWithdrawalEntry");
            double mo = (Double) context.lookup("java:comp/env/maxOverdraftEntry");
            double hf = (Double) context.lookup("java:comp/env/handlingFeeEntry");

            System.out.println("InitialContext lookup, maxWithdrawal: " + mw);
            System.out.println("InitialContext lookup, maxOverdraft:  " + mo);
            System.out.println("InitialContext lookup, handlingFee:   " + hf);
            
        } catch (NamingException ex) {
            System.out.println("NamingException: " + ex.getMessage());
        }

        // Lookup environment entries in local JNDI namespace, by using EJBContext/SessionContext.
        double mw = (Double) sessionContext.lookup("maxWithdrawalEntry");
        double mo = (Double) sessionContext.lookup("maxOverdraftEntry");
        double hf = (Double) sessionContext.lookup("handlingFeeEntry");

        System.out.println("EJBContext lookup, maxWithdrawal: " + mw);
        System.out.println("EJBContext lookup, maxOverdraft:  " + mo);
        System.out.println("EJBContext lookup, handlingFee:   " + hf);
    }
    
    
    // Method to use external resources (such as data sources, message queues, etc.)
    @Override
    public void useExternalResources() {

        System.out.println("===== In useExternalResources() method =====");
        
        try {

            // Use injected external resources.
            Connection c1 = myDatabase.getConnection();
            System.out.println("Injection used to obtain DataSource resources.");
            
            // Lookup external resources in global JNDI namespace.
            InitialContext context = new InitialContext();
            DataSource d2 = (DataSource) context.lookup("java:/DerbyDS");
            Connection c2 = d2.getConnection();
            System.out.println("InitialContext used to obtain DataSource resources from global JNDI namespace.");

            // Lookup external resources in local JNDI namespace (see @Resource annotations on bean class).
            DataSource d3 = (DataSource) sessionContext.lookup("jdbc/local/DerbyDS");
            Connection c3 = d3.getConnection();
            System.out.println("SessionContext used to obtain DataSource resources from local JNDI namespace.");
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());

        } catch (NamingException ex) {
            System.out.println("NamingException: " + ex.getMessage());

        } 
    }

    
    // Method to display the bindings in a specified JNDI context.
    @Override
    public void displayBindings(String name) {

        System.out.println("===== In displayBindings(" + name + ") method =====");
        
        try {
            doDisplayBindings(new InitialContext(), name);
        } catch (NamingException ex) {
            System.out.println("Naming exception: " + ex);
        }
    }

    
    // Helper method, called recursively to display bindings in a context (and its sub-contexts).
    private void doDisplayBindings(Context context, String name) throws NamingException {

        NamingEnumeration<Binding> enumerator = context.listBindings(name);
        while (enumerator.hasMore()) {
            Binding b = enumerator.next();
            if (b.getObject() instanceof Context) {
                doDisplayBindings(context, b.getName());
            } else {
                System.out.println("Name: " + b.getName() + ", object: " + b.getObject());
            }
        }
    }


    // CMT business method, to create the Accounts table in the database.
    @Override
    public void createAccountsTable() throws AccountException, SQLException {
        
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
            System.out.println("Created Accounts table.");

        } finally {
            this.closeConnection(cn);
        }
    }

    
    // CMT business method, to drop the Accounts table in the database.
    @Override
    public void dropAccountsTable() throws AccountException, SQLException {

        Connection cn = null;
        try {
            String sql = "DROP TABLE Accounts";

            cn = myDatabase.getConnection();
            Statement st = cn.createStatement();
            st.executeUpdate(sql);
            System.out.println("Dropped Accounts table.");

        } finally {
            this.closeConnection(cn);
        }
    }

    
    // CMT business method, to insert an account into the Accounts table.
    @Override
    public void createAccount(int accountNumber, String customerName, String customerAddress) throws AccountException, SQLException {

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
                System.out.println("Throwing AccountException in createAccount(), cannot create account.");
                throw new AccountException("Cannot create account.");
            } else {
                System.out.println("Created account " + accountNumber);
            }

        } finally {
            this.closeConnection(cn);
        }
    }


    // CMT business method, to deposit money into an account.
    @Override
    public void deposit(int accountNumber, double amount) throws AccountException, SQLException {

        Connection cn = null;
        try {
            String sql = "UPDATE Accounts SET Balance = Balance + ? WHERE AccountId = ?";

            cn = myDatabase.getConnection();
            PreparedStatement st = cn.prepareStatement(sql);
            st.setDouble(1, amount);
            st.setInt(2, accountNumber);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected != 1) {
                System.out.println("Throwing AccountException in deposit(), invalid account number.");
                throw new AccountException("Cannot deposit into account.");
            }
            System.out.println("Deposited " + amount + " into account " + accountNumber);

        } finally {
            this.closeConnection(cn);
        }
    }

    
    // CMT business method, to withdraw money from an account.
    @Override
    public void withdraw(int accountNumber, double amount) throws AccountException, SQLException {

        Connection cn = null;
        try {
            String sql = "UPDATE Accounts SET Balance = Balance - ? WHERE AccountId = ?";

            cn = myDatabase.getConnection();
            PreparedStatement st = cn.prepareStatement(sql);
            st.setDouble(1, amount);
            st.setInt(2, accountNumber);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected != 1) {
                System.out.println("Throwing AccountException in withdraw(), invalid account number.");
                throw new AccountException("Cannot withdraw from account.");
            }
            System.out.println("Withdrawn " + amount + " from account " + accountNumber);

        } finally {
            this.closeConnection(cn);
        }
    }

    
    // CMT business method (no transaction required), to get the balance for an account.
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public double getBalance(int accountNumber) throws AccountException, SQLException {

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
                System.out.println("Got balance for account " + accountNumber);
            } else {
                System.out.println("Throwing AccountException in getBalance(), invalid account number.");
                throw new AccountException("Cannot get balance for account.");
            }

        } finally {
            this.closeConnection(cn);
        }
        return balance;
    }


    // CMT business method that simulates rollback scenarios.
    @Override
    public void simulateRollbackScenarios(String testMode) throws AccountException, SQLException {
        
        // Invoke some "OK" methods within the transaction.
        deposit(1, 100);
        deposit(1, 200);
        deposit(1, 300);
        System.out.println("Balance at the moment: " + getBalance(1));
        
        // Do something that might cause the transaction to rollback.
        if (testMode.equals("MARK_ROLLBACK")) {
            sessionContext.setRollbackOnly();
            
        } else if (testMode.equals("SYSTEM_EXCEPTION")) { 
            throw new NullPointerException("Simulating a system exception (NullPointerException).");
            
        } else if (testMode.equals("APPLICATION_EXCEPTION")) { 
            throw new SQLException("Simulating an application exception (not marked for rollback).");
            
        } else if (testMode.equals("ROLLBACK_APPLICATION_EXCEPTION")) {
            throw new AccountException("Simulating an application exception (marked for rollback).");
        }
    }
    

    // CMT business method that simulates rollback scenarios.
    @Override
    public void simulateUserTransactionRollbackScenarios(String testMode) throws AccountException, SQLException {
        
        // We can invoke some "OK" methods within the client-initiated transaction. 
        // deposit(1, 10);
        // deposit(1, 20);
        // deposit(1, 30);
        // System.out.println("Balance at the moment: " + getBalance(1));

        // Do something that might cause the transaction to rollback.
        if (testMode.equals("MARK_ROLLBACK")) {
            sessionContext.setRollbackOnly();
            
        } else if (testMode.equals("SYSTEM_EXCEPTION")) {
            throw new NullPointerException("Simulating a system exception (NullPointerException).");
        
        } else if (testMode.equals("APPLICATION_EXCEPTION")) {  
            throw new SQLException("Simulating an application exception (not marked for rollback).");
        
        } else if (testMode.equals("ROLLBACK_APPLICATION_EXCEPTION")) {  
            throw new AccountException("Simulating an application exception (marked for rollback).");
        }
    }

    
    // Helper method.
    private void closeConnection(Connection cn) {
        if (cn != null) {
            try { cn.close(); } catch (SQLException ex) {}
        }
    }
}
