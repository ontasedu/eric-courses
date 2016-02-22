package demos.sessionbeans.ejb;

import javax.ejb.Remote;

@Remote
public interface TempConverterBeanRemote {
    double fToC(double fahr);
    double cToF(double celsius);  
}
