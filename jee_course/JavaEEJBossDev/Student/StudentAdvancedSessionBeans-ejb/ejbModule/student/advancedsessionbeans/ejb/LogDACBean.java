package student.advancedsessionbeans.ejb;

import javax.ejb.Stateless;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.Date;

@Stateless
public class LogDACBean implements LogDACBeanLocal {
    
    private DataSource myDatabase;

    @Override
    public void createTable() throws SQLException {

        Connection cn = null;
        try {
            String sql = "CREATE TABLE Log ( " +
                         "Timestamp       VARCHAR(30) NOT NULL, " +
                         "AccountId       INT         NOT NULL, " +
                         "Description     VARCHAR(50) NOT NULL  " +
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
            String sql = "DROP TABLE Log";

            cn = myDatabase.getConnection();
            Statement st = cn.createStatement();
            st.executeUpdate(sql);

        } finally {
            this.closeConnection(cn);
        }
    }

    @Override
    public void insertActivity(int accountNumber, String description) throws SQLException, AccountException {

        Connection cn = null;
        try {
            String sql = "INSERT INTO Log VALUES (?, ?, ?)";

            cn = myDatabase.getConnection();
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, new Date().toString());
            st.setInt(2, accountNumber);
            st.setString(3, description);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected != 1) {
                throw new AccountException("Cannot log activity.");
            }
            
        } finally {
            this.closeConnection(cn);
        }
    }

    @Override
    public void dumpActivities() throws SQLException {

        Connection cn = null;
        try {
            String sql = "SELECT Timestamp, AccountId, Description FROM Log";

            cn = myDatabase.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("Activity log:");
            while (rs.next()) {
                System.out.println("\t"          + rs.getString("Timestamp") + 
                                   "\tAccount: " + rs.getInt("AccountId")    +
                                   "\t"          + rs.getString("Description"));
            }
            
        } finally {
            this.closeConnection(cn);
        }
    } 

    private void closeConnection(Connection cn) {
        if (cn != null) {
            try { cn.close(); } catch (SQLException ex) {}
        }
    }
}
