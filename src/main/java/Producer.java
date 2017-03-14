import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by bse71 on 14.03.2017.
 */

public class Producer implements Runnable {

    @Override
    public void run() {
        ActiveMQConnectionFactory factory =
                new ActiveMQConnectionFactory("vm://localhost");
        try {
            Connection myConnection = factory.createConnection();
            myConnection.start();
            Session session = myConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("Dest");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
                String line;
                while ((line = br.readLine()) != null) {
                    producer.send(session.createTextMessage(br.readLine()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            session.close();
            myConnection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
