package com.java_multithreading.lecture01_starting_threads;

/**
 * Created by sofia on 7/7/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/StartingThreads_1/ApplicationExtends.java
 */
class ThreadRunner extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello: " + i + " Thread: " + Thread.currentThread().getName());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class AppExtendingThread {

    public static void main(String[] args) {
        ThreadRunner runner1 = new ThreadRunner();
        runner1.start();

        ThreadRunner runner2 = new ThreadRunner();
        runner2.start();
    }

}


