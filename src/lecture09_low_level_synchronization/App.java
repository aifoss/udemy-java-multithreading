package lecture09_low_level_synchronization;

/**
 * Created by sofia on 7/8/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/LowLevelProducerConsumer_9/App.java
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {

                }
            }
        });

        t1.start();
        t2.start();

//        t1.join();
//        t2.join();

        Thread.sleep(10000);
        System.exit(0);
    }

}
