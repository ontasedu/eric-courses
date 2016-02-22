package student.advancedsessionbeans.ejb;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class AccountException extends Exception {
    
	private static final long serialVersionUID = 1L;

	public AccountException() {
        super();
    }
    
    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, Exception innerException) {
        super(message, innerException);
    }
}
