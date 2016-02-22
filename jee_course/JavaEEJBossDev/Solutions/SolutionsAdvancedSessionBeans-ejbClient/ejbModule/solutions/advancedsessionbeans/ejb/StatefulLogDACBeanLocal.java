package solutions.advancedsessionbeans.ejb;

import javax.ejb.Local;

@Local
public interface StatefulLogDACBeanLocal {
	void insertActivity(int accountNumber, String description);
}
