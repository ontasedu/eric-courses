package demos.sessionbeans.ejb;

import javax.ejb.Local;

@Local
public interface TempConverterBeanLocal {
    double fToC(double fahr);
    double cToF(double celsius);    
}
