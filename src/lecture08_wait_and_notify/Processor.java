package lecture08_wait_and_notify;

import java.util.Scanner;

/**
 * Created by sofia on 7/8/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/WaitAndNotify_8/Processor.java
 */
public class Processor {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running ...");
            wait();
            System.out.println("Producer thread resumed");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        Thread.sleep(2000);

        synchronized (this) {
            System.out.println("Consumer thread running ...");

            System.out.println("Waiting for return key");
            scanner.nextLine();
            System.out.println("Return key pressed");

            notify();

            Thread.sleep(5000);

            System.out.println("Consumer thread done");
        }
    }

}
