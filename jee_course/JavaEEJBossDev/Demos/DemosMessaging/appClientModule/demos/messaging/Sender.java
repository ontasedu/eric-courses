package demos.messaging;

import javax.jms.*;
import javax.naming.*;
import java.io.*;

public class Sender {

    public static void main(String[] args) {
        
        QueueConnection queueConnection = null;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("What is the JNDI name of the queue connection factory? ");
            String queueConnectionFactoryName = br.readLine();

            System.out.println("What is the JNDI name of the queue? ");
            String queueName = br.readLine();

            System.out.println("How many messages do you want to send? ");
            int numMessages = Integer.parseInt(br.readLine());

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

            // Create a QueueSender
            QueueSender queueSender = queueSession.createSender(queue);

            // Create a text message
            TextMessage message = queueSession.createTextMessage();

            // Auto-generate and send some messages
            for (int i = 0; i < numMessages; i++) {
                message.setText("Hello, this is message " + (i + 1));
                System.out.println("Sending message: " + message.getText());
                queueSender.send(message);
            }

            // Send an empty message, to signify end of transmission
            queueSender.send(queueSession.createMessage());

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
