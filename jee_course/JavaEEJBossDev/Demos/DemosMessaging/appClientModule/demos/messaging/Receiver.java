package demos.messaging;

import javax.jms.*;
import javax.naming.*;
import java.io.*;

public class Receiver {

    public static void main(String[] args) {

        QueueConnection queueConnection = null;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("What is the JNDI name of the queue connection factory? ");
            String queueConnectionFactoryName = br.readLine();

            System.out.println("What is the JNDI name of the queue? ");
            String queueName = br.readLine();

            // Create a JNDI InitialContext
            InitialContext context = new InitialContext();

            // Look up the connection factory
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup(queueConnectionFactoryName);

            // Look up the queue
            Queue queue = (Queue) context.lookup(queueName);

            // Create a connection to the queue
            queueConnection = queueConnectionFactory.createQueueConnection();

            // Create session from the connection    
            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create a QueueReceiver
            QueueReceiver queueReceiver = queueSession.createReceiver(queue);

            // Start the queue connection, so that we can start receiving messages
            queueConnection.start();

            // Loop, to receive messages synchronously. 
            // Keep looping until we get a non-TextMessage message.
            while (true) {
                Message m = queueReceiver.receive(1);
                if (m != null) {
                    if (m instanceof TextMessage) {
                        TextMessage message = (TextMessage) m;
                        System.out.println("Reading message: " + message.getText());
                    } else {
                        break;
                    }
                }
            }

        } catch (JMSException ex) {
            System.out.println("JMSException occurred: " + ex);

        } catch (NamingException ex) {
            System.out.println("NamingException occurred: " + ex);

        } catch (Exception ex) {
            System.out.println("General Exception occurred: " + ex);

        } finally {
            if (queueConnection != null) {
                try {
                    queueConnection.close();
                } catch (JMSException e) {
                }
            }
        }
    }
}