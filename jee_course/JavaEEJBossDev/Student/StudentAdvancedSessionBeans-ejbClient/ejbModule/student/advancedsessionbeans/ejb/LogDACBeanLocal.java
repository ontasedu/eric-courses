package student.advancedsessionbeans.ejb;

import javax.ejb.Local;
import java.sql.SQLException;

@Local
public interface LogDACBeanLocal {

    public void createTable() throws SQLException;
    public void dropTable() throws SQLException;

    public void insertActivity(int accountNumber, String description) throws SQLException, AccountException;
    public void dumpActivities() throws SQLException;
}
