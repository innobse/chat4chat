import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by bse71 on 14.03.2017.
 */

public class Consumer implements Runnable {
    private static final int COUNT_MESSAGE = 100;

    @Override
    public void run() {
        ActiveMQConnectionFactory factory =
                new ActiveMQConnectionFactory("vm://localhost");
        try {
            Connection myConnection = factory.createConnection();
            myConnection.start();
            Session session = myConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("Dest");
            MessageConsumer messageConsumer = session.createConsumer(destination);
            Message message;
            int count = 0;
            while (count++ < COUNT_MESSAGE) {
                if ((message = messageConsumer.receive()) != null)
                System.out.println(((TextMessage) message).getText());
            }
            session.close();
            myConnection.close();
            System.out.println("END!");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}