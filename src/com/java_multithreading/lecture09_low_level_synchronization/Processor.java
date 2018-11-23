package com.java_multithreading.lecture09_low_level_synchronization;

import java.util.LinkedList;
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
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/LowLevelProducerConsumer_9/Processor.java
 */
public class Processor {

    private final int LIMIT = 10;
    private LinkedList<Integer> list = new LinkedList<>();
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }

                list.add(value);

                System.out.println("Producer added: " + value + "; Queue size = " + list.size());

                value++;

                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();

        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }

                int value = list.removeFirst();

                System.out.println("Consumer removed: " + value + "; Queue size = " + list.size());

                lock.notify();
            }

            Thread.sleep(random.nextInt(1000));
        }
    }

}
