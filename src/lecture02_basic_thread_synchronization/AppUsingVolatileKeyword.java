package lecture02_basic_thread_synchronization;

import java.util.Scanner;

/**
 * Created by sofia on 7/7/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/VolatileKeyword_2/App.java
 */
class Processor extends Thread {

    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Running");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }

}

public class AppUsingVolatileKeyword {

    public static void main(String[] args) {
        Processor proc = new Processor();
        proc.start();

//        System.out.println("Enter something to stop the thread,\nVolatile variable running will be forced to true :");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        proc.shutdown();
    }

}
