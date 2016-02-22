package demos.sessionbeans.ejb;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/* 
 * Note: to specify a JNDI name for the bean, define a mappedName attribute 
 * in the @Stateless annotation. For example:
 *     @Stateless(mappedName="MyTempConverterBean")
 *     public class TempConverterBean ... etc.
 */     
@Stateless
@LocalBean
public class TempConverterBean implements TempConverterBeanRemote, TempConverterBeanLocal {

    public double fToC(double fahr)  {
        return (fahr - 32) * 5 / 9;
    }

    public double cToF(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    @PostConstruct
    public void myPostConstructMethod() {
        System.out.println("Bean created at: " + new Date());
    }

    @PreDestroy
    public void myPreDestroyMethod() {
        System.out.println("Bean destroyed at: " + new Date());
    }
}
