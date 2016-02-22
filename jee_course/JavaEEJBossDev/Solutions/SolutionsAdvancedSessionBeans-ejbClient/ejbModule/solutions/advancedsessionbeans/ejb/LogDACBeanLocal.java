package solutions.advancedsessionbeans.ejb;

import java.sql.SQLException;
import javax.ejb.Local;

@Local
public interface LogDACBeanLocal {
    public void createTable() throws SQLException;
    public void dropTable() throws SQLException;

    public void insertActivity(int accountNumber, String description) throws SQLException, AccountException;
    public void dumpActivities() throws SQLException;
}
