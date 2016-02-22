package demos.jndi;

import javax.naming.*;

public class Bank implements javax.naming.Referenceable
{
	private String name;

	public Bank()
	{}

	public Bank(String n)
	{
		name = n;
	}

	public Reference getReference() throws NamingException
  	{
    		return new Reference(Bank.class.getName(),
		                     new StringRefAddr("Bankname", name),
		                     BankFactory.class.getName(),
		                     null);
  	}

	public String toString()
	{
		return name;
	}
}