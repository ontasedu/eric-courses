package solutions.advancedsessionbeans.ejb;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;

// Private class, holds info about one record to write to the Log database.
class PendingActivity {
    public int accountNumber;
    public String description;
    
    public PendingActivity(int n, String d) {
        accountNumber = n;
        description = d;
    }
}

@Stateful
public class StatefulLogDACBean implements StatefulLogDACBeanLocal, SessionSynchronization {

    @EJB
    private LogDACBeanLocal logDACBean;
    
    private ArrayList<PendingActivity> pendingActivities;
    
    public void afterBegin() throws EJBException, RemoteException {
        System.out.println("In StatefulLogDACBeanBean afterBegin().");
        pendingActivities = new ArrayList<PendingActivity>();
    }

    @Override
    public void insertActivity(int accountNumber, String description) {
        System.out.println("In StatefulLogDACBeanBean insertActivity(), account number " + accountNumber + " : " + description);
        pendingActivities.add(new PendingActivity(accountNumber, description));
    }

    public void beforeCompletion() throws EJBException, RemoteException {
        System.out.println("In StatefulLogDACBeanBean beforeCompletion().");
        
        try {
            int i = 0;
            for (PendingActivity p : pendingActivities) {
                logDACBean.insertActivity(p.accountNumber, p.description); 
                
                if (++i == 5) {
                    throw new AccountException("Simulating an exception during the 'commit' phase of a stateful session bean.");
                }
            }

        } catch (SQLException ex) {
            throw new EJBException(ex);

        } catch (AccountException ex) {
            throw new EJBException(ex);
        }
    }
    
    public void afterCompletion(boolean committed) throws EJBException, RemoteException {
        if (committed) {
            System.out.println("In StatefulLogDACBeanBean afterCompletion(), committed=true.");
        } else {
            System.out.println("In StatefulLogDACBeanBean afterCompletion(), committed=false.");
        }
    }
}
