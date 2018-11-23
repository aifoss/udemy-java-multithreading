package lecture10_reentrant_locks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sofia on 7/8/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/ReentrantLocks_10/Runner.java
 */
public class Runner {

    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        System.out.println("Inside firstThread()");

        System.out.println("First thread acquired lock");
        lock.lock();

        System.out.println("First thread waiting");
        cond.await();

        System.out.println("First thread woken up");

        try {
            increment();
            System.out.println("First thread incremented count");
        } finally {
            lock.unlock();
            System.out.println("First thread released lock");
        }
    }

    public void secondThread() throws InterruptedException {
        System.out.println("Inside secondThread()");

        System.out.println("Second thread sleeping");
        Thread.sleep(1000);

        System.out.println("Second thread acquired lock");
        lock.lock();

        System.out.println("Press the return key");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key");

        System.out.println("Second thread signaling");
        cond.signal();

        try {
            increment();
            System.out.println("Second thread incremented count");
        } finally {
            lock.unlock();
            System.out.println("Second thread released lock");
        }
    }

    public void finish() {
        System.out.println("Count is: "+count);
    }

}
