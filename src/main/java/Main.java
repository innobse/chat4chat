/**
 * Created by bse71 on 14.03.2017.
 */

public class Main {
    public static void main(String[] args) {
        Thread consumer = new Thread(new Consumer());
        consumer.start();
        Thread producer = new Thread(new Producer());
        producer.start();
    }
}
