package com.java_multithreading.lecture10_reentrant_locks;

/**
 * Created by sofia on 7/8/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/ReentrantLocks_10/App.java
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException e) {

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException e) {

                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finish();
    }

}
