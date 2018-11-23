package lecture13_callable_and_future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by sofia on 7/8/18.
 */

/**
 * Sources:
 * Udemy: Java Multithreading
 */

/**
 * Refs:
 * https://github.com/Beerkay/JavaMultiThreading/blob/master/JavaMultiThreadingCodes/src/CallableAndFuture_13/App.java
 */
public class App {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);

                if (duration > 2000) {
                    throw new TimeoutException("Sleeping too long");
                }

                System.out.println("Starting ...");

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {

                }

                System.out.println("Finished");

                return duration;
            }
        });

        executor.shutdown();

        try {
            System.out.println("Result: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            TimeoutException te = (TimeoutException) e.getCause();
            System.out.println(te.getMessage());
        }
    }

}
