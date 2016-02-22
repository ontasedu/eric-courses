package demos.jndi;

import javax.naming.*;
import java.util.Hashtable;
import javax.naming.spi.*;

public class BankFactory implements ObjectFactory
{
  public BankFactory()  {}

  public Object getObjectInstance(Object object,
                                  Name name, 
                                  Context ctx,
                                  Hashtable<?,?> env) throws Exception
  {
    if (object instanceof Reference)
    {
      Reference reference = (Reference)object;
      if (reference.getClassName().equals(Bank.class.getName()))
      {
        RefAddr address = reference.get("Bankname");
        if (address != null)
          return new Bank((String)address.getContent());
      }
    }
    return null;
  }
}
