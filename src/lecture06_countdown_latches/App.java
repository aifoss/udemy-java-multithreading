package lecture06_countdown_latches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sofia on 7/8/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/CountDownLatch_6/App.java
 */
class Processor implements Runnable {

    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Started thread "+Thread.currentThread().getName());

        System.out.println("Latch count before: "+latch.getCount());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }

        System.out.println("Completed thread "+Thread.currentThread().getName());

        latch.countDown();

        System.out.println("Latch count after: "+latch.getCount());
    }

}

public class App {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executor.submit(new Processor(latch));
        }

        executor.shutdown();

        try {
            latch.await();
        } catch (InterruptedException e) {

        }

        System.out.println("Completed");
    }

}
