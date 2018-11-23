package com.java_multithreading.lecture12_semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sofia on 7/8/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/Semaphores_12/App.java
 */
public class App {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);

    }

}
