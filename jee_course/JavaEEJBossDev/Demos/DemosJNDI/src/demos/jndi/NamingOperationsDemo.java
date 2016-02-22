package demos.jndi;

import javax.naming.*;
import java.io.*;
import java.util.*;

public class NamingOperationsDemo {

    private static Context context = null;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try {
            Hashtable<String, String> env = new Hashtable<String, String>();

            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.fscontext.RefFSContextFactory");

            env.put(Context.PROVIDER_URL, "file://C:/JavaEEJBossDev/Temp");

            context = new InitialContext(env);

            // Display the main menu of options
            String choice = "";
            do {
                System.out.println("\n--------------------------------------------------------------");
                System.out.println("1. Create and bind an object");
                System.out.println("2. Lookup an object by name");
                System.out.println("3. List all names");
                System.out.println("4. List all objects");
                System.out.println("5. Rename an object");
                System.out.println("6. Unbind an object from a name");
                System.out.println("7. Quit");
                System.out.println("\n====>");

                choice = br.readLine();

                // Create and bind an object
                if (choice.equals("1")) {
                    System.out.println("JNDI name: ");
                    String jndiname = br.readLine();

                    System.out.println("Name of bank (e.g. Barclays): ");
                    String bankname = br.readLine();

                    context.bind(jndiname, new Bank(bankname));
                    
                } 
                // Lookup an object by name
                else if (choice.equals("2")) {
                    System.out.println("JNDI name: ");
                    String jndiname = br.readLine();

                    Object obj = context.lookup(jndiname);
                    if (obj instanceof Bank) {
                        Bank bank = (Bank) obj;
                        System.out.println("Object details: " + bank);
                    }
                
                } 
                // List all names                
                else if (choice.equals("3")) {
                    NamingEnumeration<NameClassPair> enumerator = context.list(".");
                    while (enumerator.hasMore()) {
                        NameClassPair nc = enumerator.next();
                        System.out.println(nc);
                    }
                    
                } 
                // List all objects
                else if (choice.equals("4")) {
                    NamingEnumeration<Binding> enumerator = context.listBindings(".");

                    while (enumerator.hasMore()) {
                        Binding bd = enumerator.next();
                        System.out.println("Name of object: " + bd.getName());
                        System.out.println("Bound object: " + bd.getObject());
                    }

                } 
                // Rename an object
                else if (choice.equals("5")) {
                    System.out.println("Current JNDI name: ");
                    String currentJndiname = br.readLine();

                    System.out.println("New JNDI name: ");
                    String newJndiname = br.readLine();

                    context.rename(currentJndiname, newJndiname);

                } 
                // Unbind an object from a name
                else if (choice.equals("6")) {
                    System.out.println("JNDI name: ");
                    String jndiname = br.readLine();

                    context.unbind(jndiname);
                }
            } while (!choice.equals("7"));

        } catch (NameAlreadyBoundException ex) {
            System.out.println("There is already an object bound to the name" +
                    " 'FinancialInstitution' in the current context");

        } catch (NamingException ex) {
            System.out.println("Some other naming exception occurred: " + ex);

        } catch (Exception ex) {
            System.out.println("Some other exception occurred: " + ex);

        }
    }
}

