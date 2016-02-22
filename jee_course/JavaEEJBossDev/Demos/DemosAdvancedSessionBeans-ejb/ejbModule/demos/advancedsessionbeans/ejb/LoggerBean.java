package demos.advancedsessionbeans.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class LoggerBean implements LoggerBeanLocal {

    @Override
    public void log(String message) {
        System.out.println("Logging message internally: " + message);
    }
}
