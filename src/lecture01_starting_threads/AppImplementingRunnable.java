package lecture01_starting_threads;

/**
 * Created by sofia on 7/7/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/StartingThreads_1/ApplicationRunnable.java
 */

class RunnableRunner implements Runnable {

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

public class AppImplementingRunnable {

    public static void main(String[] args) {
        Thread t1 = new Thread(new RunnableRunner());
        Thread t2 = new Thread(new RunnableRunner());

        t1.start();
        t2.start();
    }

}
