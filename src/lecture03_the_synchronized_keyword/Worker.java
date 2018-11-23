package lecture03_the_synchronized_keyword;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sofia on 7/7/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/JoiningAndSynchronizeThreads_3/Worker.java
 */
public class Worker {

    private int count = 0;

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.doWork();
    }

    public synchronized void increment(String threadName) throws InterruptedException {
        count++;
        System.out.println("Thread: "+threadName+", Count="+count);
    }

    public void doWork() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    try {
                        increment(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    try {
                        increment(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count = "+count);
    }

}
