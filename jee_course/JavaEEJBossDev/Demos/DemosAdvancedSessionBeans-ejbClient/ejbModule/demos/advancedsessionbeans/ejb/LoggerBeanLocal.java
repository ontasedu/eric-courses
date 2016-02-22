package demos.advancedsessionbeans.ejb;

import javax.ejb.Local;

@Local
public interface LoggerBeanLocal {
	void log(String message);
}
