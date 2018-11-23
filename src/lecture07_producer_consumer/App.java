package lecture07_producer_consumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by sofia on 7/8/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/ProducerConsumer_7/App.java
 */
public class App {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e) {

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {

                }
            }
        });

        t1.start();
        t2.start();

//        t1.join();
//        t2.join();

        Thread.sleep(20000);
        System.exit(0);
    }

    private static void producer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            Integer item = random.nextInt(100);
            queue.put(item);
            System.out.println("Put "+item+"; Queue size = "+queue.size());
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            Thread.sleep(100);
            if (random.nextInt(10) == 0) {
                Integer item = queue.take();
                System.out.println("Taken "+item+"; Queue size = "+queue.size());
            }
        }
    }

}
