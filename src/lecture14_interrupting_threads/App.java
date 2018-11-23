package lecture14_interrupting_threads;

import java.util.Random;

/**
 * Created by sofia on 7/8/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/InterruptingThreads14/App.java
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting ...");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();

                for (int i = 0; i < 1E8; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted: "+Thread.currentThread().getName());
                        break;
                    }

                    Math.sin(random.nextDouble());
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();

                for (int i = 0; i < 1E8; i++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted: "+Thread.currentThread().getName());
                        break;
                    }

                    Math.sin(random.nextDouble());
                }
            }
        });

        t1.start();
        t2.start();

        Thread.sleep(500);

        t1.interrupt();
        t2.interrupt();

        t1.join();
        t2.join();

        System.out.println("Finished");
    }

}
